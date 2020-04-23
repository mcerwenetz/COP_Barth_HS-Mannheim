package script_examples.chap7;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import script_examples.Util;

public class CompletitionService {

	
	public static void main(String[] args) {
		
		ExecutorService exs = Executors.newCachedThreadPool();
		
		ExecutorCompletionService<Integer> cs = new ExecutorCompletionService<Integer>(exs);
		
		final Random r = new Random();
		
		for (int i = 0; i < 100; i++) {
			final int id = i;
			Callable<Integer> ci = () -> {
				Util.sleep(r.nextInt(1000));
				return id;
			};
			cs.submit(ci);
		}
		Util.resetTime();
		for (int i = 0; i < 10; i++) {
//			Future<Integer> fi;
			try {
				cs.take();
//				fi = cs.take();
				//System.out.println(fi.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Util.getTimeMilis() + " msecs nach 10");
		for (int i = 0; i < 90; i++) {
			try {
				cs.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(Util.getTimeMilis() + " msecs für den Rest");
		exs.shutdown();
	}
}
