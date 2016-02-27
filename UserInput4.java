import java.io.Console;
import java.lang.NumberFormatException;

public class UserInput4
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
         System.out.println(str1+" is not an integer ");

         try {
            float dec1 = Float.parseFloat(str1);
            System.out.println(dec1+" is a decimal value");
         }
         catch (NumberFormatException nfe2) {
           System.out.println(str1+" is not a decimal");
         }
      }
   }
}

