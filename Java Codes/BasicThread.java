//import java.lang.Runnable
public class BasicThread{

    public static void main(String[]args){
        //creating runnable using the inner class
       Runnable rn = new Runnable() {
        @Override
         public void run(){
            System.out.println("Hello from the thread " + Thread.currentThread().getName());
         }
                
            };
            Thread t = new Thread(rn, "mydemothread");
            t.start();
        }

}
    
