package a2.t4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.Util;

public class OddEvenQueues {
	
	public static void main(String[] args) {
		BlockingQueue<Integer> list = new LinkedBlockingQueue<Integer>();
		Lock prodLock = new ReentrantLock();
		
		ProducerThread[] prods = new ProducerThread[16];
		for (int i = 0; i < prods.length; i++) {
			prods[i] = new ProducerThread(list, prodLock);
		}
		Distributer d = new Distributer(prodLock, list);
		
		for (ProducerThread producerThread : prods) {
			producerThread.start();
		}
		d.start();
		
		for (ProducerThread producerThread : prods) {
			Util.join(producerThread);
		}
		d.setProdDone(true);
		Util.join(d);
		
		
	}

}
