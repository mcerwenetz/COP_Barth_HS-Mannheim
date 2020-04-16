package a2.t3;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.Util;

public class OddEven {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Lock lock = new ReentrantLock();
//		MyCondition con = new MyCondition();
		Condition con = lock.newCondition();
		Boolean done = false;

		ProducerThread[] prodThreads = new ProducerThread[10];
		ConsumerThread[] conThreads = new ConsumerThread[2];

		for (int i = 0; i < prodThreads.length; i++) {
			prodThreads[i] = new ProducerThread(list, lock, con);
		}

		for (int i = 0; i < conThreads.length; i++) {
			Boolean even;
			if (i % 2 == 0) {
				even = true;
			} else {
				even = false;
			}
			conThreads[i] = new ConsumerThread(list, lock, even, con, done);
		}

		for (ProducerThread thread : prodThreads) {
			thread.start();
		}

		for (ConsumerThread thread : conThreads) {
			thread.start();
		}

		for (ProducerThread thread : prodThreads) {
			Util.join(thread);
		}

		for (ConsumerThread thread : conThreads) {
			thread.setDone(true);
			Util.join(thread);
		}

		for (ConsumerThread consumerThread : conThreads) {
			System.out.println("is even:" + consumerThread.even + " " + consumerThread.sum);
		}
	}

}
