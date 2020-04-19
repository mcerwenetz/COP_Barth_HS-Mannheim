package script_examples.chap4.conditions;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer extends Thread {

	private Queue<Integer> queue;
	private Lock lock;
	private int howmany;
	private Condition qCondition;

	public Producer(Queue<Integer> queue, Lock lock, int howmany, Condition qCondition) {
		this.queue=queue;
		this.lock=lock;
		this.howmany=howmany;
		this.qCondition=qCondition;
	}


	@Override
	public void run() {
		for (int i = 1; i <= howmany; i++) {
			Thread.yield();
			lock.lock();
			try {
				this.queue.add(i);
				qCondition.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}
