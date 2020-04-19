package script_examples.chap4.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer extends Thread {

	private BlockingQueue<Integer> queue;
	private int howmany;

	public Producer(BlockingQueue<Integer> queue, int howmany) {
		this.queue=queue;
		this.howmany=howmany;
	}


	@Override
	public void run() {
		for (int i = 1; i <= howmany; i++) {
			Thread.yield();
			try {
				queue.put(i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			}
		}
	}
}
