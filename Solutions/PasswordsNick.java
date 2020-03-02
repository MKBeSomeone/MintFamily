import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordsNick 
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		Scanner kb = new Scanner(new File("passwords.dat"));
		int cases = kb.nextInt();
		while(cases-->0)
		{
			ArrayList<Filter> a = new ArrayList<Filter>();
			int rules = kb.nextInt();
			if(kb.hasNext())
				kb.nextLine();
			for(int i = 0; i < rules; i++)
			{
				a.add(new Filter(kb.nextLine().split("\\s+")));
			}
			int passwords = kb.nextInt();
			for(int i = 0; i < passwords; i++)
			{
				int x = 0;
				String s = kb.next();
				for(Filter f : a)
				{
					if(f.matches(s))
					{
						x++;
					}
				}
				System.out.println(s + ": " + getStrength((double)x/rules));
			}
			System.out.println();
		}
	}
	
	public static String getStrength(double d)
	{
		if(d == 1)
			return "Excellent";
		else if(d >= .75)
			return "Strong";
		else if(d >= .5)
			return "Moderate";
		else if(d >= .25)
			return "Weak";
		else if(d > 0)
			return "Terrible";
		else
			return "Abysmal";
	}
	
	private static class Filter
	{
		boolean b;
		boolean direct;
		boolean length;
		int n;
		char c;
		String pattern;
		
		public Filter(String[] s)
		{
			if(s[0].equals("Length"))
			{
				length = true;
				n = Integer.parseInt(s[1]);
				c = s[2].charAt(0);
			}
			else
			{
				setPattern(s);
			}
		}
		
		public void setPattern(String[] s)
		{
			switch(s[0])
			{
			case "Capital":
				b = s[1].equals("true");
				pattern = ".*[A-Z].*";
				break;
			case "Lowercase":
				b = s[1].equals("true");
				pattern = ".*[a-z].*";
				break;
			case "Number":
				b = s[1].equals("true");
				pattern = ".*[0-9].*";
				break;
			case "Special":
				b = s[2].equals("true");
				pattern = ".*["+s[1]+"].*";
				break;
			case "Follow":
				b = s[4].equals("true");
				direct = s[3].equals("true");
				pattern = ".*["+s[2]+(direct?"][":"].*[")+s[1]+"].*";
				break;
			}
		}
		
		public boolean matches(String s)
		{
			boolean ans = false;
			if(length)
			{
				switch(c)
				{
				case '<':
					return s.length() <= n;
				case '=':
					return s.length() == n;
				default:
					return s.length() >= n;
				}
			}
			else
			{
				ans = s.matches(pattern);
			}
			return !(b ^ ans);
		}
		
		public String toString()
		{
			return length + " " + n + " " + c + " " + b + " " + pattern;
		}
	}
}
