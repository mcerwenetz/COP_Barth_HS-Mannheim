package script_examples.chap4.conditions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProdCon {

	public static void main(String[] args) {
		int howmany = 1000;
		Queue<Integer> queue = new LinkedList<Integer>();
		Lock lock = new ReentrantLock();
		Condition q = lock.newCondition();
		
		Producer producer = new Producer(queue, lock, howmany, q);
		Consumer consumer1 = new Consumer(queue, lock,q);
		Consumer consumer2 = new Consumer(queue, lock,q);
		producer.start();
		consumer1.start();
		consumer2.start();

		try {
			producer.join();
			consumer1.done.set(true);
			consumer2.done.set(true);
			lock.lock();
			try {
				q.signalAll();
			} finally {
				lock.unlock();
			}
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
