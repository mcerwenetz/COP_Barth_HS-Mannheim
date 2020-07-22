package script_examples.chap7.threadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedTP {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ExecutorService threadpool = Executors.newCachedThreadPool();
		ExecutorCompletionService<Void> executorCompletionService = new ExecutorCompletionService<Void>(threadpool);

		
		for (int i = 0; i < 3; i++) {
		
			Callable<Void> callable = new Callable<Void>() {

				@Override
				public Void call() throws Exception {
					System.out.println("Hello from Callable");
					Thread.sleep(1000);
					return null;
				}
			};

			executorCompletionService.submit(callable);
		}
		
		for (int i = 0; i < 3; i++) {
			try {
				executorCompletionService.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		threadpool.shutdown();
	}
}
