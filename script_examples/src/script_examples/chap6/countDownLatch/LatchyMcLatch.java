package script_examples.chap6.countDownLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class LatchyMcLatch {

	public static void main(String[] args) {

		final int threadnum = 100;
		final int limit = threadnum/2;

		CountDownLatch latch = new CountDownLatch(limit);
		
		Random random = new Random();

		Thread[] threads = new Thread[threadnum];

		for (Thread thread : threads) {
			int i =0;
			thread = new Thread() {
				@Override
				public void run() {
					System.out.println("Thread is ready");
					try {
						Thread.sleep(Math.abs(random.nextInt()%5000));
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					latch.countDown();
					try {
						latch.await();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Go");
				}
			};
			thread.start();
		}
	}
}
