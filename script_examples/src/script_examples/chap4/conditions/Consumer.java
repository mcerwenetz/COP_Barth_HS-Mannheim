package script_examples.chap4.conditions;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consumer extends Thread {
	
	private Queue<Integer> queue;
	private Lock lock;
	private Condition qCondition;
	
	AtomicBoolean done = new AtomicBoolean(false);
	long sum =0, misses =0;
	
	public Consumer(Queue<Integer> queue, Lock lock, Condition condition) {
		this.queue = queue;
		this.lock = lock;
		this.qCondition=condition;
	}
	
//	public void run() {
//		while(!done.get()) {
//			Integer val= null;
//			synchronized (lock) {
//				if(!queue.isEmpty()) {
//					val = queue.remove();
//				}
//			}
//			if(val != null) {
//				sum+=val;
//			}else {
//				Thread.yield();
//				misses++;
//			}
//		}
//	}
	
	
	//with wait
	
	public void run() {
		while(!done.get()) {
			Integer value = null;
			lock.lock();
			try {
				while (queue.isEmpty() && !done.get()) {
					qCondition.await();
				}
				if(!queue.isEmpty()) {
					value=queue.remove();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				lock.unlock();
			}
			if (value != null) {
				sum+=value;
			}else {
				misses+=1;
			}
		}
	}
}
