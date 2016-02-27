
public class SimpleLoop3
{
    public SimpleLoop3() { }

    public void forloop()
    {
       int maxCount = 30;

       // multiple of 3 or 5 but not 15 
       for(int x=0; x<maxCount; x++) 
       {
/*
          if(...) {
             System.out.println("...");
          }
          else if(...) {
             System.out.println("...");
          }
          else if(...) {
             System.out.println("...");
          }
*/
       }
    }

    public static void main (String args[])
    {
        SimpleLoop3 sl3 = new SimpleLoop3(); 
        sl3.forloop();
    }
}

