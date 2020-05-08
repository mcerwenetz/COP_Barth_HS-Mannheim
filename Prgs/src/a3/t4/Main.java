package a3.t4;

import java.util.concurrent.RejectedExecutionException;

import util.Util;

public class Main {

	public static void main(String[] args) {

		MyExecutor myExecutor = new MyExecutor(2);

		Runnable r = new Runnable() {
			public void run() {
				Util.sleep(10);
				System.out.println("runnable running");
			}
		};

		myExecutor.doBlock(true);
		for (int i = 0; i < 10; i++) {
			try {
				myExecutor.execute(r);
			} catch (RejectedExecutionException e) {
				System.out.println("Queue full");
			}
		}
		myExecutor.shutdown();
//		System.out.println("Limit reached " + myExecutor.limitReached + " times");
	}

}
