import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput2
{
   public static void main (String args[]) 
   { 
      System.out.print("Enter a number: ");
      Scanner scan = new Scanner(System.in);

      try {
         int num1 = scan.nextInt();
         System.out.println(num1+" is a number");
      } 
      catch (InputMismatchException ime) {
         System.out.println("You did not enter a number");
         System.out.println("===> Before the stack trace...");
         ime.printStackTrace();
         System.out.println("===> After the stack trace...");
      }
   }
}

