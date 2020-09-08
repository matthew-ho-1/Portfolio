//Name - Matthew Ho
//Date - 11/5
//Period - 2nd

import java.util.Scanner;
import java.io.*;

public class ExpressionTester
{
	public static void main ( String[] args ) throws IOException
	{
		System.out.println("Postfix ==> evaluation");
		Scanner input = new Scanner(new File("Postfix.dat"));
		while(input.hasNextLine())
		{
			Postfix post = new Postfix(input.nextLine());
			System.out.println(post  + " ==> "+ post.evaluate());
		}
		
		System.out.println("\n\n");	
		System.out.println("Infix ==> Postfix ==> evaluation");	
		input = new Scanner(new File("Infix.dat"));
		while(input.hasNextLine())
		{
			Infix in = new Infix(input.nextLine());
			System.out.print(in + " ==> " + in.toPostfix());
			Postfix post = new Postfix(in.toPostfix());
			System.out.println(" ==> " + post.evaluate());
		}	
		
	}
}