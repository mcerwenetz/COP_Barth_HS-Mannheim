package a2.t4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class Distributer extends Thread {

	AtomicBoolean prodDone;

	BlockingQueue<Integer> prodDistQueue;
	BlockingQueue<Integer> oddConsumerQueue;
	BlockingQueue<Integer> evenConsumerQueue;

	public Distributer(AtomicBoolean prodDone, BlockingQueue<Integer> prodDistQueue,
			BlockingQueue<Integer> oddConsumerQueue, BlockingQueue<Integer> evenConsumerQueue) {
		super();
		this.prodDone = prodDone;
		this.prodDistQueue = prodDistQueue;
		this.oddConsumerQueue = oddConsumerQueue;
		this.evenConsumerQueue = evenConsumerQueue;
	}

	@Override
	public void run() {
		Integer integer = 0;
		while (!prodDone.get() || !prodDistQueue.isEmpty()) {
			try {
//				integer = prodDistQueue.poll(100, TimeUnit.MILLISECONDS);
				integer=prodDistQueue.take();
//				if (integer == null) {
//					continue;
//				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if ((integer % 2) == 0) {
				try {
					evenConsumerQueue.put(integer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					oddConsumerQueue.put(integer);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}