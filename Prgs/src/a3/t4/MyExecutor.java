package a3.t4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyExecutor implements Executor {

	Integer maxThreads;
	Integer limitReached = 0;
	private Boolean doBlock;
	Queue<Runnable> runnables;
	Lock lock;
	Condition condition;
	AtomicBoolean shutdown;
	Queue<Thread> threads;

	public MyExecutor(Integer maxThreads) {

		Runnable doWork = () -> {
			while (!shutdown.get()) {
				try {
					lock.lock();
					while (runnables.isEmpty()) {
						condition.await();
					}
					if (shutdown.get()) {
						break;
					}
					Runnable r = runnables.poll();
					r.run();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		};

		lock = new ReentrantLock();
		condition = lock.newCondition();
		this.maxThreads = maxThreads;
		doBlock = true;
		shutdown = new AtomicBoolean(false);
		runnables = new LinkedList<Runnable>();
		threads = new LinkedList<Thread>();

		for (int i = 0; i < maxThreads; i++) {
			Thread t = new Thread(doWork);
			t.start();
			threads.add(t);
		}

	}

	public void doBlock(Boolean block) {
		this.doBlock = block;
	}

	@Override
	public void execute(Runnable command) {
		if (runnables.size() >= maxThreads) {
			limitReached++;
			if (doBlock) {
				System.out.println("Queue full");
				return;
			} else {
				throw new RejectedExecutionException();
			}
		} else {
			try {
				lock.lock();
				runnables.add(command);
				condition.signalAll();
			} finally {
				lock.unlock();
			}
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
