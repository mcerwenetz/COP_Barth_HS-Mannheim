package script_examples.chap4.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer extends Thread {
	
	private BlockingQueue<Integer> queue;
	long sum =0, misses =0;
	
	public Consumer(BlockingQueue<Integer> queue) {
		this.queue = queue;
	}
	

	//with wait
	
	public void run() {
		Integer value;
		try {
			while (true) {
				value = queue.take();
				sum+=value;
			}
		} catch (InterruptedException e) {
			misses++;
			do {
				value = queue.poll();
				if (value!=null) {
					sum+=sum;
				}
			}while(value!= null);
		}
	}
}
