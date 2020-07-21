package script_examples.chap6.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Waiter extends Thread {
	
	static CyclicBarrier barrier = new CyclicBarrier(RunCylicBarrier.WAITERS.length, new InBetween());
	static final Integer MAXSLEEP = 100;
	volatile int slept;
	
	Random random = new Random();
	
	@Override
	public void run() {
		try {
			while(!isInterrupted()) {
				slept = random.nextInt(MAXSLEEP);
				Thread.sleep(slept);
				System.out.println("Counting up");
				barrier.await();
			}
		} catch (InterruptedException e) {
			return;
		} catch (BrokenBarrierException ignored) {
		}
	}
	
	public int getSlept() {
		return slept;
	}
	
	public void shutDown() {
		this.interrupt();
	}
	

}
