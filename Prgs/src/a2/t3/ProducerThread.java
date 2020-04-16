package a2.t3;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import util.Util;

public class ProducerThread extends Thread {
	
	LinkedList<Integer> list;
	Lock lock;
	Condition con;

	public ProducerThread(LinkedList<Integer> list, Lock lock, Condition con) {
		this.list=list;
		this.lock=lock;
		this.con = con;
	}

		@Override
		public void run() {
			Random rand = new Random();
			for(int i = 0; i < 5; i++) {
				Integer a = Math.abs(rand.nextInt()%400);
				try {
					lock.lock();
					list.add(a);
					con.signalAll();
				} finally {
					lock.unlock();
				}
				Util.sleep(4);
			}
		}
}
