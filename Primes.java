
public class Primes
{
    public Primes() { }

    public void findDivisors(int num)
    {
       int divCount = 0;

/*
       for(int x=2; x<num; x++) 
       {
          if(...) {  
             ++divCount;
          }
       }

       if(divCount .... ) {
          System.out.println(num+" has ... divisors (not prime)");
       } else { 
          System.out.println(num+" is prime");
       } 
*/
    }

    public static void main (String args[])
    {
        int maxCount = 15;
        Primes primes = new Primes(); 

        for(int x=2; x<maxCount; x++) {
           primes.findDivisors(x);
        }
    }
}

