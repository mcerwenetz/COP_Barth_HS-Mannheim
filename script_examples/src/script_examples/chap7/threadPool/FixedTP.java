package script_examples.chap7.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedTP {

	public static void main(String[] args) {
		ExecutorService threadpool = Executors.newCachedThreadPool();

		for (int i = 0; i < 100; i++) {
			threadpool.submit(() -> {
				System.out.println("Hello from Runnable");
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		}
		threadpool.shutdown();
	}
}
