import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.math.BigInteger;

public class Pyramint
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner read = new Scanner(new File("pyramint.dat"));
		int t = read.nextInt();
		while(t-->0)
		{
			int n = read.nextInt();
			long ans = 0;
			for(int i = 0; i<=n; i++)
			{
				ans += (long)i*i;
			}
			System.out.println(ans);
			
			/*Shown below is mathematically equivalent code not designated as the given
			 *solution, beacuse this problem should not require use of the BigInteger class.
			 *This method, however, runs in constant time, and is therefore optimal.
			 *
			 *Formula: f(n) = n*(n+1)*(2*n+1)/6
			 *
			 *BigInteger o1 = BigInteger.valueOf(n);
			 *BigInteger o2 = o1.add(BigInteger.ONE); //n+1
			 *BigInteger o3 = o1.shiftLeft(1).add(BigInteger.ONE); //2n+1
			 *BigInteger six = BigInteger.valueOf(6);
			 *System.out.println(o1.multiply(o2).multiply(o3).divide(six));
			 */
		}
		read.close();
	}
}
			
				