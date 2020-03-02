import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Roadside
{
	public static void main(String[] args) throws FileNotFoundException
	{
		Scanner read = new Scanner(new File("roadside.dat"));
		int n = read.nextInt();
		int m = read.nextInt();
		int q = read.nextInt();
		int[][] dis = new int[n][n];
		
		//fill each position in the table with 'infinity'
		//do not actually use Integer.MAX_VALUE, as this will cause overflow issues during addition
		for(int i = 0; i<n; i++)
		{
			for(int j = 0; j<n; j++)
			{
				//A large number used to indicate no road exists.
				dis[i][j] = 999_999_999;
			}
		}
		for(int i = 0; i<m; i++)
		{
			int a = read.nextInt();
			int b = read.nextInt();
			int w = read.nextInt();
			
			//Establish the smaller of the two possible roads as the ideal choice between two locations.
			//This will replace the 999_999_999 placeholder value with the given road lengths.
			dis[a][b] = Math.min(dis[a][b], w);
			dis[b][a] = Math.min(dis[b][a], w);
		}
		
		//Floyd-Warshall
		for(int k = 0; k<n; k++)
		{
			for(int i = 0; i<n; i++)
			{
				for(int j = 0; j<n; j++)
				{
					if(dis[i][k] + dis[k][j] < dis[i][j])
					{
						dis[i][j] = dis[i][k] + dis[k][j];
					}
				}
			}
		}
		
		//Process input queries
		for(int i = 0; i<q; i++)
		{
			int u = read.nextInt();
			int v = read.nextInt();
			System.out.println(dis[u][v]);
		}
		read.close();
	}
}