package a3.t4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MyExecutor implements Executor {

	Integer numberOfThreads;
	AtomicInteger numberOfSubmittedRunnables;
	long keepAliveTime;
	BlockingQueue<Runnable> workQueue;
	MyThreadPoolExecutor myThreadPoolExecutor;
	Integer queueWasFull;

	public MyExecutor(Integer maximumPoolSize) {
		this.numberOfThreads = maximumPoolSize;
		this.numberOfSubmittedRunnables = new AtomicInteger(0);
		this.keepAliveTime = 100;
		workQueue = new LinkedBlockingQueue<Runnable>();
		myThreadPoolExecutor = new MyThreadPoolExecutor(0, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS,
				workQueue, numberOfSubmittedRunnables);
		queueWasFull=0;
	}

	@Override
	public void execute(Runnable command) {
		if (this.numberOfSubmittedRunnables.get() >= numberOfThreads) {
//			System.out.println("Queue full");
			queueWasFull++;
			return;
		} else {
			myThreadPoolExecutor.submit(command);
		}

	}

}
