import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Frustums
{

	public static void main(String[] args) throws FileNotFoundException 
	{
		//Both of these Methods are suitable for solving this problem, choose your preference
		//methodOne();
		methodTwo();
	}
	
	public static void methodOne() throws FileNotFoundException
	{
		Scanner kb = new Scanner(new File("frustums.dat"));
		double r1 = kb.nextDouble();
		double R = kb.nextDouble();
		double H = kb.nextDouble();
		double h3 = kb.nextDouble();
		double h2 = kb.nextDouble();
		double m = (R-r1)/H;
		double r3 = R-m*h3;
		double r2 = r3-m*h2;
		System.out.printf("%.3f%n", 1.0/3*Math.PI*h2*(r3*r3+r2*r2+r2*r3));
		kb.close();
	}
	
	public static void methodTwo() throws FileNotFoundException
	{
		Scanner inputFile = new Scanner(new File(("frustums.dat")));
		
		//Inputs
		double r1 = inputFile.nextDouble();
		double R = inputFile.nextDouble();
		double H = inputFile.nextDouble();
		double h3 = inputFile.nextDouble();
		double h2 = inputFile.nextDouble();
		
		//Calculates the angle of the frustrum
		double theta = Math.atan(H/(R-r1));
		//calculates the remaining height other than the two given
		double h1 = H - h3 - h2;
		//Calculates the radius of the 2nd circle from the bottom
		double r3 = (h1+h2)/Math.tan(theta)+r1;
		//Calculates the radius of the 3rd circle from the bottom
		double r2 = h1/Math.tan(theta)+r1;
		
		//Output
		System.out.printf("%.3f%n", 1.0/3*Math.PI*h2*(Math.pow(r3,2)+Math.pow(r2, 2)+r2*r3));
		
		//Scanner Closing Statement
		inputFile.close();

	}
}
