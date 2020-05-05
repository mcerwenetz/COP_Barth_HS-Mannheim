package a3.t4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPoolExecutor extends ThreadPoolExecutor {
	AtomicInteger numberOfSubmittedRunnables;

	public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
			BlockingQueue<Runnable> workQueue, AtomicInteger numberOfSubmittedRunnables) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		this.numberOfSubmittedRunnables=numberOfSubmittedRunnables;
	}
	
	@Override
	protected void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		numberOfSubmittedRunnables.incrementAndGet();
//		System.out.println(numberOfSubmittedRunnables.incrementAndGet() + " threads running");
		
	}
	
	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		numberOfSubmittedRunnables.decrementAndGet();
//		System.out.println(numberOfSubmittedRunnables.decrementAndGet() + " threads running");
	}
	
	@Override
	public void shutdown() {
		super.shutdown();
	}

}
