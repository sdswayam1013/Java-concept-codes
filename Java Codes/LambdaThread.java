public class LambdaThread{
    public static void main(String[]args){
        Runnable r1 = () -> System.out.println("name of the thread" + Thread.currentThread().getName()); //Runnable r = new Runnable()
        
        Thread t1 = new Thread(r1, "mythread");
        t1.start();
        
    }
}