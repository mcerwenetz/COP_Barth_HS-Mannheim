package script_examples.chap4.prodcon;

import java.util.Queue;

public class Producer extends Thread {
	
	private Queue<Integer> queue;
	private Object lock;
	
	private int howmany;
	
	public Producer(Queue<Integer> queue, Object lock, int howmany) {
		this.queue=queue;
		this.lock=lock;
		this.howmany=howmany;
	}
	
//	@Override
//	public void run() {
//		for (int i = 1; i <= howmany; i++) {
//			Thread.yield();
//			synchronized (lock) {
//				this.queue.add(i);
//			}
//		}
//	}
	
	@Override
	public void run() {
		for (int i = 1; i <= howmany; i++) {
			Thread.yield();
			synchronized (lock) {
				this.queue.add(i);
				this.lock.notify();
			}
		}
	}
}
