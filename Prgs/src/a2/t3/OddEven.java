package a2.t3;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.Sum;
import util.Util;

public class OddEven {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		Lock lock = new ReentrantLock();
//		MyCondition con = new MyCondition();
		Condition con = lock.newCondition();
		Sum evensum = new Sum(0);
		Sum oddsum = new Sum(0);

		
		ProducerThread[] prodThreads = new ProducerThread[1];
		ConsumerThread[] conThreads = new ConsumerThread[2];

		for (int i = 0; i < prodThreads.length; i++) {
			prodThreads[i] = new ProducerThread(list, lock, con);
		}

		for (int i = 0; i < conThreads.length; i++) {
			if (i % 2 == 0) {
				conThreads[i]=new EvenConsumerThread(list, lock, con, evensum);
			} else {
				conThreads[i]=new OddConsumerThread(list, lock, con, oddsum);
			}
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

		System.out.println("Evensum: " + evensum + "\nOddsum: " + oddsum);
	}

}
