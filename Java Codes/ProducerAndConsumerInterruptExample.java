import java.util.concurrent.*;

class Producer implements Runnable{
    private final BlockingQueue<Integer> queue;

public Producer(BlockingQueue<Integer> q){
this.queue = q;
}

@Override
public void run() {
    int i = 0;
    try{
        //runs until thus thread is interrupted(thread interuption handling)
        while(!Thread.currentThread().isInterrupted()){
            queue.put(i); //blocks if the queue is full
            System.out.println(Thread.currentThread().getName() + "Produced" + i);
            i++;
            Thread.sleep(500);
        }
    }
    catch(InterruptedException e){
        //when interuppted during put() or sleep(), this block runs
        System.out.println(Thread.currentThread().getName() + "interuppted , stoping producer");
        //When a thread is sleeping, waiting, or blocking (e.g., Thread.sleep(), wait(), join()),
        //and it gets interrupted, Java throws an InterruptedException —
        //and automatically clears the flag to false!

        Thread.currentThread().interrupt(); //restore the interrupt flag to true again

    }
    System.out.println(Thread.currentThread().getName() + "exited");    
    }

}
class Consumer implements Runnable{
    private final BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> q){
        this.queue = q;
    }
    @Override
    public void run(){
        try{
            //runs until the thread is interrupted
            while(!Thread.currentThread().isInterrupted()){
                Integer item = queue.take(); //blocks if queue is empty
                 System.out.println(Thread.currentThread().getName() + "Consumed" + item);
                Thread.sleep(600);
            }

        }
        catch (InterruptedException e)
        {
            System.out.println(Thread.currentThread().getName() + "interupted , stopping consumer");
            Thread.currentThread().interrupt(); //restore the interrupt flag

        }
        System.out.println(Thread.currentThread().getName() + "exited");
    }
}

public class ProducerAndConsumerInterruptExample{
    public static void main(String[]args) throws InterruptedException{
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(new Producer(sharedQueue) , "producer-thread");
        Thread consumer = new Thread(new Consumer(sharedQueue) , "consumer-thread");

        producer.start();
        consumer.start();

        Thread.sleep(5000); //let both threads run for few seconds

        //gracefully stoping both the threads by interupting them
        System.out.println("Main thread: sending interupt signal to both threads");
        producer.interrupt();
        consumer.interrupt();

        //wait them to finish
        producer.join();
        consumer.join();

        System.out.println("All threads stopped cleanly");
    }

}