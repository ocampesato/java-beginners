import java.io.Console;
import java.lang.NumberFormatException;
import java.util.Scanner;

public class UserInput3
{
   public static void main (String args[]) 
   { 
      Console input = System.console();
      String str1 = input.readLine("Enter a number: ");

      try {
         int num1 = Integer.parseInt(str1);
         System.out.println(str1+" is a number");
      }
      catch (NumberFormatException nfe) {
         System.out.println(str1+" is not a number");
      }
   }
}

