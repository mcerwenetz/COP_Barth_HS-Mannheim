package a3.t4;

import java.util.concurrent.RejectedExecutionException;

public class Main {

	public static void main(String[] args) {

		MyExecutor myExecutor = new MyExecutor(2);

		Runnable r = new Runnable() {
			public void run() {
				System.out.println("running");
			}
		};

		myExecutor.doBlock(false);
		for (int i = 0; i < 10; i++) {
			try {
				myExecutor.execute(r);
			} catch (RejectedExecutionException e) {
				System.out.println("Queue full");
			}
		}
		myExecutor.shutdown();
		System.out.println("Limit reached " + myExecutor.limitReached + " times");
	}

}
