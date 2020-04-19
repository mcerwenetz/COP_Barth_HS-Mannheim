package a2.t5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import util.Sum;

public class Consumer extends Thread {
	
	BlockingQueue<Integer> queue;
	AtomicBoolean keep_reading;
	CheckWhere checkWhere;
	Sum sum;

	public Consumer(BlockingQueue<Integer> queue, AtomicBoolean keep_reading, CheckWhere checkWhere) {
		super();
		this.queue = queue;
		this.keep_reading = keep_reading;
		this.checkWhere=checkWhere;
		sum = new Sum(0);
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		Integer integer =0;
		while (keep_reading.get() || !queue.isEmpty()) {
			integer=queue.poll();
			if (integer==null) {
				continue;
			}
			if(this.checkWhere.checkWhere(integer)) {
				sum.increaseBy(integer);
			}
			else {
				try {
					queue.put(integer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public Sum getSum() {
		return this.sum;
	}


}
