package a2.t4;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;

import util.Util;

public class ProducerThread extends Thread {
	
	BlockingQueue<Integer> queue;
	Lock prodLock;

	public ProducerThread(BlockingQueue<Integer> list, Lock lock) {
		this.queue=list;
		this.prodLock=lock;
	}

		@Override
		public void run() {
			Random rand = new Random();
			for(int i = 0; i < 200; i++) {
				Integer a = Math.abs(rand.nextInt()%400);
				prodLock.lock();
				try {
					queue.add(a);
				} finally {
					prodLock.unlock();
				}
				Util.sleep(4);
			}
		}
}
