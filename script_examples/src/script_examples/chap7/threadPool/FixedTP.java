package script_examples.chap7.threadPool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FixedTP {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ExecutorService threadpool = Executors.newCachedThreadPool();

		List<Callable<Void>> callables = new LinkedList<Callable<Void>>();
		
		for (int i = 0; i < 3; i++) {
		
			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					System.out.println("Hello from Callable");
					Thread.sleep(1000);
					return null;
				}
			};

			callables.add(callable);
		}
		
		try {
			threadpool.invokeAll(callables);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			 
		try {
			threadpool.awaitTermination(2, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		threadpool.shutdown();
	}
}
