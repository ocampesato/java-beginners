package acme.com;

import java.util.Scanner;

public class UserInput 
{
   public static void main (String args[]) 
   { 
      System.out.print("Enter a number: ");
      Scanner scan = new Scanner(System.in);
      int num1 = scan.nextInt();
      System.out.println("[package acme.com]");
      System.out.println("you entered a number");
   }
}

