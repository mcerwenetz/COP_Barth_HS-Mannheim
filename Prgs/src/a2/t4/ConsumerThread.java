package a2.t4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import util.Sum;

public class ConsumerThread extends Thread {

	AtomicBoolean conDone;
	BlockingQueue<Integer> consumerQueue;
	Sum sum;

	public ConsumerThread(AtomicBoolean conDone, BlockingQueue<Integer> consumerQueue, Sum sum) {
		super();
		this.conDone = conDone;
		this.consumerQueue = consumerQueue;
		this.sum = sum;
	}

	@Override
	public void run() {
		Integer integer = 0;
		while (!conDone.get() || !consumerQueue.isEmpty()) {
			try {
				integer = consumerQueue.take();
//				integer = consumerQueue.poll(200, TimeUnit.MILLISECONDS);
//				if (integer == null)
//					continue;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sum.increaseBy(integer);
		}
	}

}
