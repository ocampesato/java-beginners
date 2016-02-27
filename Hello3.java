
public class Hello3
{
   public Hello3() 
   {
      System.out.println("Constructor1");
      sayHello();
   }

   public Hello3(int x) 
   {
      System.out.println("Constructor2");
      System.out.println("x: "+x);
      sayHello();
   }

   public Hello3(int x, int y) 
   {
      System.out.println("Constructor3");
      System.out.println("x: "+x+" y: "+y);
      sayHello();
   }

   public void sayHello()
   {
      System.out.println("Hello World from Hello3");
   }

   public static void main (String args[]) 
   {   
      Hello3 hw1 = new Hello3(); 
      Hello3 hw2 = new Hello3(10); 
      Hello3 hw3 = new Hello3(10, 40); 
   }   
}

