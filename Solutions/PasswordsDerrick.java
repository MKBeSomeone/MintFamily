import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class PasswordsDerrick {

	public static void main(String[] args) throws Throwable {
		Scanner kb = new Scanner(new File("passwords.dat"));
		int times = Integer.parseInt(kb.nextLine().trim());
		
		while(times-->0)
		{
			int n = Integer.parseInt(kb.nextLine().trim());
			int m;
			ArrayList<String[]> list = new ArrayList<>();
			for(int x = 0; x < n; x++)
				list.add(kb.nextLine().split(" "));
			m = Integer.parseInt(kb.nextLine().trim());
			
			for(int x = 0; x < m; x++)
			{
				String pass = kb.nextLine();
				int c = n;
				for(int y = 0; y < list.size(); y++)
				{
					switch(list.get(y)[0])
					{
						case "Capital": c+=Capital(pass,list.get(y)[1]); break;
						case "Lowercase": c+=LowerCase(pass,list.get(y)[1]); break;
						case "Number": c+=Number(pass,list.get(y)[1]); break;
						case "Special": c+=Special(pass,list.get(y)[1].charAt(0),list.get(y)[2]); break;
						case "Length": c+=Length(pass,Integer.parseInt(list.get(y)[1]),list.get(y)[2]); break;
						case "Follow": int p = Follow(pass,list.get(y)[1].charAt(0),list.get(y)[2].charAt(0),list.get(y)[3],list.get(y)[4]); 
						//System.out.println(p == 0 ? "does" : "does not");
						c+=p;
						break;
					}
				}
				double strength = (double)c/n;
				if(strength == 1)
				{
					System.out.println(pass+": Excellent");
				}
				else if(strength >= .75)
				{
					System.out.println(pass+": Strong");
				}
				else if(strength >= .50)
				{
					System.out.println(pass+": Moderate");
				}
				else if(strength >= .25)
				{
					System.out.println(pass+": Weak");
				}
				else if(strength >= .01)
				{
					System.out.println(pass+": Terrible");
				}
				else
				{
					System.out.println(pass+": Abysmal");
				}
			}
			System.out.println("=====");
		}
	}

	private static int Follow(String pass, char A, char B, String bool1, String bool2) {
		if(bool2.equals("true")) // A must follow B
		{
			if(bool1.equals("true")) // Directly
			{
				//System.out.println("A must follow B directly");
				for(int x = 0; x < pass.length()-1; x++)
				{
					if(pass.charAt(x) == B && pass.charAt(x+1) == A)
						return 0;
				}
				return -1;
			}
			else // subsequently
			{
				//System.out.println("A must follow B subsequently");
				boolean foundB = false;
				for(int x = 0; x < pass.length(); x++)
				{
					if(pass.charAt(x) == B)
						foundB = true;
					if(pass.charAt(x) == A && foundB)
						return 0;
				}
				return -1;
			}
		}
		else // A cant follow B
		{
			if(bool1.equals("true")) // Directly
			{
				//System.out.println("A must not follow B directly");
				for(int x = pass.length()-1; x > 0; x--)
				{
					if(pass.charAt(x) == A && pass.charAt(x-1) == B)
							return  -1;
				}
				return 0;
			}
			else // subsequently
			{
				//System.out.println("A must not follow B subsequently");
				boolean foundB = false;
				for(int x = 0; x < pass.length(); x++)
				{
					if(pass.charAt(x) == B)
						foundB = true;
					if(pass.charAt(x) == A && foundB)
						return -1;
				}
				return 0;
			}
		}
	}

	private static int Length(String pass, int N, String C) {
		if(C.equals("<"))
			return pass.length() > N ? -1 : 0;
		else if(C.equals(">"))
			return pass.length() < N ? -1 : 0;
		else
			return pass.length() != N ? -1 : 0;
	}

	private static int Special(String pass, char c,String bool) {
		if(bool.equals("true"))
		{
			for(int x = 0; x < pass.length(); x++)
			{
				if(pass.charAt(x) == c)
					return 0;
			}
			return -1;
		}
		else
		{
			for(int x = 0; x < pass.length(); x++)
			{
				if(pass.charAt(x) == c)
					return -1;
			}
			return 0;
		}
	}

	private static int Number(String pass, String bool) {
		if(bool.equals("true"))
			return pass.matches("(.*[0-9]+.*)") ? 0 : -1;
		return pass.matches("(.*[0-9]+.*)") ? -1 : 0;
	}

	private static int LowerCase(String pass, String bool) {
		if(bool.equals("true"))
		{
			for(int x = 0; x < pass.length(); x++)
			{
				if(Character.isLowerCase(pass.charAt(x)))
					return 0;
			}
			return -1;
		}
		else
		{	
			for(int x = 0; x < pass.length(); x++)
			{
				if(Character.isLowerCase(pass.charAt(x)))
					return -1;
			}
		}
		return 0;
	}

	private static int Capital(String pass, String bool) {
		if(bool.equals("true"))
		{
			for(int x = 0; x < pass.length(); x++)
			{
				if(Character.isUpperCase(pass.charAt(x)))
					return 0;
			}
			return -1;
		}
		else
		{	
			for(int x = 0; x < pass.length(); x++)
			{
				if(Character.isUpperCase(pass.charAt(x)))
					return -1;
			}
		}
		return 0;
	}
}