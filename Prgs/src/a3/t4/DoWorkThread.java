package a3.t4;

import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class DoWorkThread extends Thread {

	Condition condition;
	AtomicBoolean shutdown;
	Queue<Runnable> runnables;
	Lock lock;
	Semaphore sem;

	public DoWorkThread(Condition condition, AtomicBoolean shutdown, Queue<Runnable> runnables, Lock lock, Semaphore sem) {
		this.condition = condition;
		this.shutdown = shutdown;
		this.runnables = runnables;
		this.lock = lock;
		this.sem=sem;
	}

	@Override
	public void run() {
		while (!shutdown.get()) {
			Runnable r=null;
			try {
				lock.lock();
				while (runnables.isEmpty()) {
					condition.await();
				}
				if (shutdown.get()) {
					break;
				}
				r = runnables.poll();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
			r.run();
			sem.release();
		}
	}
}
