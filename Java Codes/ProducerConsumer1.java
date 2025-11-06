import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue; //Blockingqueue is an interface, it defines a contract for
                                           // queues that support operations that wait when the queue is empty or full
import java.util.concurrent.LinkedBlockingQueue;//is a class(implementation), a concrete class that implements
                                                 //the Blockingqueue interface using linked node structure

class Producer implements Runnable {

         private BlockingQueue<Integer> queue; //Shared queue reference where Producer will put items.

        public Producer(BlockingQueue<Integer> q){
        this.queue = q;
    }
    @Override
    public void run(){ //run method contains the code that runs when this thread starts
     try{
        int i = 0; //Starts producing integers from 0
        while(true){ //infinite loop-keeping producing items
         queue.put(i); //puts an item into the queue
                       //if the queue is full(capacity=5), it waits automatically(gets blocked)
        System.out.println("produced" + i);
        i++;          //increament he value for the next item
        Thread.sleep(500); //pause for 0.5 sec to stimulate time taken to produce

        }
    }
        
        catch(InterruptedException e){
         Thread.currentThread().interrupt();
         e.printStackTrace();

        }
     }
    }

    
    //CONSUMER CLASS
class Consumer implements Runnable{//Consumer is also a runnable i.e runs in another thread

    private BlockingQueue<Integer> queue; //same shared queue reference

    public Consumer(BlockingQueue<Integer> q){
        this.queue = q;
    }

    @Override
    public void run(){
        try{
            while(true) {//infinite loop-keeps consuming
                Integer i = queue.take(); //Takes an item from the queue
                                          //If the queue is empty, waits automatically
                System.out.println("Consumed" + i);
                Thread.sleep(1000); //Stimulate slow consumption so that producer
                                    // can produce enough to consume

        }
    }
        catch(InterruptedException e){
         Thread.currentThread().interrupt();
         e.printStackTrace();

        }
}

}

//MAIN CLASS 
public class ProducerConsumer1{
    public static void main(String[]args){
        BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);
        //created a blockingqueue with capacity 5
        //ArrayBlockingQueue is a fixed size queue, threadsafe and automatically blocks

        //Creation of Producer and Consumer threas,both sharing the same queue
        Thread producer = new Thread(new Producer(sharedQueue));
        //public Thread(Runnable target)
        //when we pass a Runnable object(like Producer) to new Thread(),we mean
        //Thus thread should execute the run() method from its Producer object

        Thread consumer = new Thread(new Consumer(sharedQueue));
     
        //Starting both threads
        producer.start(); //Starts Producer's run() in a new thread
        consumer.start(); //Starts Consumer's run(0 in another thread)
    }
}