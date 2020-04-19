package script_examples.chap4.prodcon;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer extends Thread {
	
	private Queue<Integer> queue;
	private Object lock;
	
	AtomicBoolean done = new AtomicBoolean(false);
	long sum =0, misses =0;
	
	public Consumer(Queue<Integer> queue, Object lock) {
		this.queue = queue;
		this.lock = lock;
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
			Integer val= null;
			synchronized (lock) {
				try {
					while(queue.isEmpty()) {
						lock.wait();
					}
					val = queue.remove();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			if(val != null) {
				sum+=val;
			}else {
				misses++;
			}
		}
	}
}
