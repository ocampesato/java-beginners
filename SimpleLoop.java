
public class SimpleLoop
{
    public SimpleLoop() { }

    public void forloop()
    {
       int maxCount = 10;

       for(int x=0; x<maxCount; x++) 
       {
           System.out.println("x = "+x+" and x*x = "+x*x);
       }
    }

    public static void main (String args[])
    {
        SimpleLoop sl = new SimpleLoop(); 
        sl.forloop();
    }
}

