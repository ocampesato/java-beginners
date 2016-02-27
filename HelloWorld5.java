public class HelloWorld5
{
   public HelloWorld5() 
   {
      this(10);
      System.out.println("Constructor1");
   }

   public HelloWorld5(int x) 
   {
      this(10, 20);
      System.out.println("Constructor2");
   }

   public HelloWorld5(int x, int y) 
   {
      sayHello();
      System.out.println("Constructor3");
   }

   public void sayHello()
   {
      System.out.println("Hello World from HelloWorld5");
   }

   public static void main (String args[]) 
   {   
      HelloWorld5 hw1 = new HelloWorld5(); 
      HelloWorld5 hw2 = new HelloWorld5(10); 
      HelloWorld5 hw3 = new HelloWorld5(10, 40); 
   }   
}

