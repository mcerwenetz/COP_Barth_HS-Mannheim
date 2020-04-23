package script_examples.chap7;

import java.util.Random;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import script_examples.Util;

public class MyThreadFactory implements ThreadFactory {

	private AtomicInteger count = new AtomicInteger(0);
	
	
	@Override
	public Thread newThread(Runnable r) {
		int id = count.incrementAndGet();
		return new MyThread(r, "MT-"+id);
	}
	
	public static void main(String[] args) {
		Random random = new Random();
		Runnable r = () -> {
		Util.sleep(random.nextInt(10000));	
		};
		
		MyThreadFactory tf = new MyThreadFactory();
		ExecutorService exs = Executors.newCachedThreadPool(tf);
		for (int i = 0; i < 100; i++) {
			exs.submit(r);
		}
		exs.shutdown();
	}
	
}
