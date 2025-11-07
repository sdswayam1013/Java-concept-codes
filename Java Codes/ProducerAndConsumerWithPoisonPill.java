import java.util.concurrent.*;

class Producer implements Runnable{
    private final BlockingQueue<Integer> queue ;
    private static final int POISON_PILL = -1; //special signal

    public Producer(BlockingQueue<Integer> q){
        this.queue = q;
    }

    @Override
    public void run() {
     try{
        for(int i= 0 ; i < 100 ; i++){
            queue.put(i);
            System.out.println("Produced" + i);
            Thread.sleep(500);
        }
        queue.put(POISON_PILL);
        System.out.println("Producer finished and inserted poison pill");
     }   
     catch(InterruptedException e){
        Thread.currentThread().interrupt();
        e.printStackTrace();

     }
    }
}

class Consumer implements Runnable{

    private final BlockingQueue<Integer> queue;
    private static final int POISON_PILL = -1;

    public Consumer(BlockingQueue<Integer> q){
        this.queue = q;
    }

    @Override
    public void run(){
        try{
            while (true) {
                Integer item = queue.take(); //wiats if queue is empty
                
                if(item.equals(POISON_PILL)){
                    System.out.println("Consumer received poison pill and exiting");
                    break; //exit the loop gracefully
                }

                System.out.println("Consumed" + item);
                Thread.sleep(1000);
            }

        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }
}

public class ProducerAndConsumerWithPoisonPill{
    public static void main(String[] args) throws InterruptedException{
          BlockingQueue<Integer> sharedQueue = new ArrayBlockingQueue<>(5);

        Thread producer = new Thread(new Producer(sharedQueue));
        Thread consumer = new Thread(new Consumer(sharedQueue));

        producer.start();
        consumer.start();

        producer.join();  // wait for producer to finish
        consumer.join();  // wait for consumer to stop
        System.out.println("All threads stopped cleanly ");
    }
    }
    /*
 *  PRODUCER–CONSUMER WITH POISON PILL (Summary)
 *
 * This program demonstrates multithreading in Java using:
 * 1. Runnable interface – to define Producer and Consumer tasks.
 * 2. BlockingQueue<Integer> – a thread-safe queue used for communication.
 * 3. ArrayBlockingQueue<>(5) – creates a bounded queue (capacity = 5),
 *    meaning at most 5 elements can exist in the queue at any time.
 *
 *  Key Concepts:
 * -------------------------------------------------------------
 * • The Producer thread puts elements into the shared queue.
 *   - If the queue is full, producer automatically BLOCKS until space is freed.
 *
 * • The Consumer thread takes elements from the shared queue.
 *   - If the queue is empty, consumer automatically BLOCKS until an item appears.
 *
 * • The queue capacity (5) controls how many items can be "in flight" at once.
 *   It is NOT the same as how many items are produced in total.
 *
 * • The `final` keyword on BlockingQueue ensures the reference cannot be 
 *   reassigned to a new queue after initialization (immutability of reference).
 *
 *  Poison Pill Pattern:
 * -------------------------------------------------------------
 * A special sentinel value (e.g., -1) is inserted into the queue by the producer
 * after finishing its normal work. When the consumer reads this poison pill,
 * it understands there’s no more data and exits its loop gracefully.
 *
 * This provides a clean shutdown mechanism without using unsafe Thread.stop().
 * For multiple consumers, one poison pill per consumer is required.
 *
 * ✅ Flow Summary:
 * -------------------------------------------------------------
 * 1. Producer creates and puts items (e.g., numbers) into the shared queue.
 * 2. After producing all items, it adds a poison pill (-1).
 * 3. Consumer keeps consuming until it encounters the poison pill, then stops.
 * 4. Main thread waits for both threads using join() and exits cleanly.
 *
 * 🧾 Analogy:
 * -------------------------------------------------------------
 * Think of the queue as a warehouse with limited shelves (capacity = 5).
 * The producer (factory worker) adds boxes, and the consumer (delivery worker)
 * removes them. When a red box (poison pill) arrives, it signals “shift over.”
 *
 */


