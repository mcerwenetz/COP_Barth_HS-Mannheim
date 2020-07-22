package script_examples.chap7.threadPool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FixedTP {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ExecutorService threadpool = Executors.newCachedThreadPool();
		List<Future<Void>> futureList  = new LinkedList<Future<Void>>();

		for (int i = 0; i < 3; i++) {
			futureList.add((Future<Void>) threadpool.submit(() -> {
				System.out.println("Hello from Runnable");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}));
		}
		try {
			threadpool.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadpool.shutdown();
		for (Future<Void> future : futureList) {
			System.out.println(future);
		}
	}
}
