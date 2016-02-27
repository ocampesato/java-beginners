import java.io.Console;
import java.io.IOException;

public class UserInput5
{
   public static void main (String args[]) 
   { 
      System.out.print("Enter a number: ");

      try {
         int num1 = System.in.read();
         System.out.println(num1+" is a number");
      }
      catch (IOException ioe) {
         System.out.println("Input is not a number");
      }
   }
}

