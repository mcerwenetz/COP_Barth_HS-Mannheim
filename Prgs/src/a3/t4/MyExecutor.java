package a3.t4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyExecutor implements Executor {

	int maxThreads;
	int limitReached = 0;
	private boolean doBlock;
	Queue<Runnable> runnables;
	Lock lock;
	Condition condition;
	AtomicBoolean shutdown;
	Thread[] threads;
	Semaphore sem;

	public MyExecutor(Integer maxThreads) {

		lock = new ReentrantLock();
		condition = lock.newCondition();
		this.maxThreads = maxThreads;
		doBlock = true;
		shutdown = new AtomicBoolean(false);
		runnables = new LinkedList<Runnable>();
		threads = new Thread[maxThreads];
		sem=new Semaphore(this.maxThreads);

		for (int i = 0; i < maxThreads; i++) {
			threads[i] = new DoWorkThread(condition, shutdown, runnables, lock, sem);
		}

		for (Thread thread : threads) {
			thread.start();
		}

	}

	public void doBlock(Boolean block) {
		this.doBlock = block;
	}

	@Override
	public void execute(Runnable command) {

		boolean acquiered=false;
		while (!acquiered) {
			acquiered=sem.tryAcquire();
			if (!acquiered && doBlock) {
				throw new RejectedExecutionException();
			}
		}
		try {
			lock.lock();
			runnables.add(command);
			condition.signalAll();
		} finally {
			lock.unlock();
		}

	}

	public void shutdown() {
		shutdown.set(true);
		for (Thread thread : threads) {
			try {
				lock.lock();
				condition.signalAll();
			} finally {
				lock.unlock();
			}
			try {
				thread.join();
			} catch (Exception e) {
			}
		}
	}
}
