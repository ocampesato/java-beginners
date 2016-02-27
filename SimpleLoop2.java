
public class SimpleLoop2
{
    public SimpleLoop2() { }

    public void forloop()
    {
       int maxCount = 10;

       for(int x=0; x<maxCount; x++) 
       {
          if(x % 2 == 0) {
             System.out.println("x = "+x+" and x*x = "+x*x);
          }
       }
    }

    public static void main (String args[])
    {
        SimpleLoop2 sl2 = new SimpleLoop2(); 
        sl2.forloop();
    }
}

