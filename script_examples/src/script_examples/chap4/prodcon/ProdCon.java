package script_examples.chap4.prodcon;

import java.util.LinkedList;
import java.util.Queue;

public class ProdCon {

	public static void main(String[] args) {
		int howmany = 10;
		Queue<Integer> queue = new LinkedList<Integer>();
		Object lock = new Object();
		
		Producer producer = new Producer(queue, lock, howmany);
		Consumer consumer = new Consumer(queue, lock);
		producer.start();
		consumer.start();
		
		try {
			producer.join();
			consumer.done.set(true);
			consumer.interrupt();
			consumer.join();
			System.out.println("sum: " + consumer.sum + " misses: " + consumer.misses);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
