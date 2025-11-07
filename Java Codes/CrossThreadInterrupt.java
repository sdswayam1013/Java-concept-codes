import java.util.concurrent.*;

public class CrossThreadInterrupt {
    public static void main(String[] args){
        Thread worker = new Thread(new Worker() , "worker-thread");
        Thread interrupter = new Thread(new Interrupter(worker), "Interrupter-thread");

        worker.start();
        interrupter.start();
    }
    
}
class Worker implements Runnable{
    public void run(){
        try{
            while(true){
                System.out.println(Thread.currentThread().getName() + "is working");
                Thread.sleep(500);
            }
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + "got interrupted so stopping");
            Thread.currentThread().interrupt(); //restore flag
        }
        }
    }

class Interrupter implements Runnable{
    private final Thread target ;

    public Interrupter(Thread target){
        this.target = target;
    }
    @Override
    public void run(){
        try{
            Thread.sleep(2000); //wait for 2 seconds before interupting
            System.out.println(Thread.currentThread().getName() + "interrupting" + target.getName());
            target.interrupt(); //interrupt the other thread
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

    }

}
