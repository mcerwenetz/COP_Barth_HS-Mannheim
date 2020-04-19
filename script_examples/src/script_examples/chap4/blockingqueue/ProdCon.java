package script_examples.chap4.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProdCon {

	public static void main(String[] args) {
		int howmany = 100;
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		Lock lock = new ReentrantLock();
		Condition q = lock.newCondition();
		
		Producer producer = new Producer(queue, howmany);
		Consumer consumer1 = new Consumer(queue);
		Consumer consumer2 = new Consumer(queue);
		producer.start();
		consumer1.start();
		consumer2.start();

		try {
			producer.join();
			consumer1.interrupt();
			consumer2.interrupt();
			consumer1.join();
			consumer2.join();
			System.out.println("consumer 1 sum: " + consumer1.sum);
			System.out.println("consumer 2 sum: " + consumer2.sum);
			System.out.println("misses: " + (consumer1.misses+consumer2.misses));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
