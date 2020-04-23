package script_examples.chap6;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableExample implements Callable<Integer> {

	Random random = new Random();

	@Override
	public Integer call() throws Exception {
		int rand;
		Thread thread = Thread.currentThread();
		do {
			rand = random.nextInt(Integer.MAX_VALUE);
			if (thread.isInterrupted()) {
				throw new InterruptedException();
			}
		} while (rand % 100000000 != 17171717);
		return rand;
	}

	public static void main(String[] args) {
		CallableExampleThread[] threads = new CallableExampleThread[4];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new CallableExampleThread();
		}
		
		for (CallableExampleThread callableExampleThread : threads) {
			callableExampleThread.start();
		}
		
		for (CallableExampleThread callableExampleThread : threads) {
			try {
				callableExampleThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
