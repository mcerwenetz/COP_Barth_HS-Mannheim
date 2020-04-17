package a2.t4;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.Sum;
import util.Util;

public class Distributer extends Thread {

	Lock prodLock;
	Boolean prodDone=false;
	BlockingQueue<Integer> queue;

	Boolean conDone;
	BlockingQueue<Integer> oddConQueue;
	BlockingQueue<Integer> evenConQueue;
	Lock evenLock;
	Lock oddLock;
	LinkedList<OddConsumerThread> oddthreads;
	LinkedList<EvenConsumerThread> eventhreads;
	Sum oddSum = new Sum(0);
	Sum evenSum = new Sum(0);


	public Distributer(Lock prodLock, BlockingQueue<Integer> queue) {
		this.prodLock = prodLock;
		this.queue = queue;

		conDone = false;
		oddConQueue = new LinkedBlockingQueue<Integer>();
		evenConQueue = new LinkedBlockingQueue<Integer>();
		evenLock = new ReentrantLock();
		oddLock = new ReentrantLock();
		oddthreads = new LinkedList<OddConsumerThread>();
		eventhreads = new LinkedList<EvenConsumerThread>();

		createConsumers();

	}

	private void createConsumers() {

		for (int i = 0; i <1; i++) {
			OddConsumerThread odd = new OddConsumerThread(oddConQueue, oddLock, oddSum);
			EvenConsumerThread even = new EvenConsumerThread(evenConQueue, evenLock, evenSum);
			oddthreads.add(odd);
			eventhreads.add(even);
		}

	}

	@Override
	public void run() {
		
		startthreads();
		
		while (!prodDone) {
			try {
				prodLock.lock();
				addtoque(queue.poll());
			} finally {
				prodLock.unlock();
			}
			
		}
		
		jointhreads();
		System.out.println("Sum of odd: " + oddSum);
		System.out.println("Sum of even: " + evenSum);

	}

	private void addtoque(Integer integer) {
		if (integer % 2 == 0) {
			try {
				evenLock.lock();
				evenConQueue.add(integer);
			} finally {
				evenLock.unlock();
			}
		} else {
			try {
				oddLock.lock();
				oddConQueue.add(integer);
			} finally {
				oddLock.unlock();
			}
		}
	}

	private void startthreads() {
		for (OddConsumerThread t : oddthreads) {
			t.start();
		}
		for (EvenConsumerThread t : eventhreads) {
			t.start();
		}
		
	}

	private void jointhreads() {
		for (OddConsumerThread t : oddthreads) {
			t.setConDone(true);
			Util.join(t);
		}
		for (EvenConsumerThread t : eventhreads) {
			t.setConDone(true);
			Util.join(t);
		}
		
	}
	
	public void setProdDone(Boolean b) {
		prodDone=b;
	}
}
