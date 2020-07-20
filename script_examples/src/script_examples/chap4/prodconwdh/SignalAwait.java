package script_examples.chap4.prodconwdh;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SignalAwait {

	Integer queueBound = 10;
	Lock lock = new ReentrantLock();
	Condition full = lock.newCondition();
	Queue<Integer> queue = new LinkedList<Integer>();
	volatile boolean keealive = true;

	public class Consumer extends Thread {

		@Override
		public void run() {
			while (keealive) {
				lock.lock();
				while (queue.size() == 0) {
					try {
						full.await();
					} catch (InterruptedException e) {
					}
				}
				System.out.println("took " + queue.remove());
				lock.unlock();
			}
		}
	}

	public class Producer extends Thread {
		Random rand = new Random();

		@Override
		public void run() {
			while (keealive) {
				lock.lock();
				queue.add(rand.nextInt());
				full.signal();
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {

		SignalAwait sa = new SignalAwait();
		Producer p = sa.new Producer();
		Consumer c = sa.new Consumer();

		p.start();
		c.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sa.keealive = false;
		c.interrupt();

	}
}
