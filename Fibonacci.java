public class Fibonacci 
{
   public Fibonacci() { }

   int calc(int n)
   {
      if(n <= 1) { return n; }
      else       { return calc(n-1)+calc(n-2); }
   }

   public static void main (String args[]) 
   {   
      int result = 0, num = 8;

      Fibonacci fib = new Fibonacci(); 
      result = fib.calc(num); 
      System.out.println("Fibonacci "+num+" = "+result);
   }   
}

