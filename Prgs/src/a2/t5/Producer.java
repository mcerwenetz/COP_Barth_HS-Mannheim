package a2.t5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Producer extends Thread {

	BlockingQueue<Integer> queue;
	AtomicBoolean keep_reading;
	
	public Producer(BlockingQueue<Integer> queue, AtomicBoolean keep_reading) {
		super();
		this.queue = queue;
		this.keep_reading = keep_reading;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			try {
				queue.put(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		keep_reading.set(false);
	}


}
