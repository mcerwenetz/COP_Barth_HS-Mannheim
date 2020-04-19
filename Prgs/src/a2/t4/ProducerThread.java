package a2.t4;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import util.Util;

public class ProducerThread extends Thread {

	BlockingQueue<Integer> queue;
	AtomicBoolean prodDone;

	public ProducerThread(BlockingQueue<Integer> list, AtomicBoolean prodDone) {
		this.queue = list;
		this.prodDone = prodDone;
	}

	@Override
	public void run() {
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			Integer a = Math.abs(rand.nextInt() % 400);
			queue.add(a);
			Util.sleep(4);
		}
	}
}
