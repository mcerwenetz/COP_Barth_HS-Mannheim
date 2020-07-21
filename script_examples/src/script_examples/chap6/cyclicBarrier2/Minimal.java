package script_examples.chap6.cyclicBarrier2;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Minimal {

	public static void main(String[] args) {

		final int parties = 2;

		AtomicBoolean run = new AtomicBoolean(true);

		Runnable barrierAction = new Runnable() {

			@Override
			public void run() {
				System.out.println("Barrier Action");
			}
		};

		Thread[] threads = new Thread[parties];

		CyclicBarrier barrier = new CyclicBarrier(parties, barrierAction);

		for (Thread thread : threads) {
			thread = new Thread() {
				@Override
				public void run() {
					while (run.get()) {
						System.out.println("Thread");
						try {
							barrier.await();
						} catch (InterruptedException | BrokenBarrierException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};
			thread.start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run.set(false);
	}

}
