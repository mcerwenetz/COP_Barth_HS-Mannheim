package script_examples.chap4.prodconwdh;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NotifyWait {

	Queue<Integer> queue = new LinkedList<Integer>();
	Lock lock = new ReentrantLock();
	volatile boolean keepalive;
	Random random = new Random();

	public class Consumer extends Thread {
		@Override
		public void run() {
			Integer got = null;
			try {
				while (keepalive) {
					synchronized (lock) {
						while (queue.isEmpty()) {
							lock.wait();
						}
						got = queue.remove();
					}
					System.out.println(got + " rausgenommen");

				}
			} catch (InterruptedException e) {

			}
		}
	}

	public class Producer extends Thread {
		@Override
		public void run() {
			while (keepalive) {
				Integer integer = random.nextInt();
				synchronized (lock) {
					queue.add(integer);
					lock.notify();
				}
				System.out.println(integer + " eingefuegt");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {

		NotifyWait main = new NotifyWait();
		Consumer mainConsumer = main.new Consumer();
		Producer mainProducer = main.new Producer();
		main.keepalive = true;

		mainProducer.start();
		mainConsumer.start();

		Thread.sleep(1);
		main.keepalive = false;
		mainConsumer.interrupt();

	}

}
