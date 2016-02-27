public class HelloWorld4
{
   public HelloWorld4() 
   {
      System.out.println("Constructor1");
      sayHello();
   }

   public HelloWorld4(int x) 
   {
      System.out.println("Constructor2");
      sayHello();
   }

   public HelloWorld4(int x, int y) 
   {
      System.out.println("Constructor3");
      sayHello();
   }

   public void sayHello()
   {
      System.out.println("Hello World from HelloWorld4");
   }

   public static void main (String args[]) 
   {   
      HelloWorld4 hw1 = new HelloWorld4(); 
      HelloWorld4 hw2 = new HelloWorld4(10); 
      HelloWorld4 hw3 = new HelloWorld4(10, 40); 
   }   
}

