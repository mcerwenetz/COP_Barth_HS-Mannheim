package a2.t3;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConsumerThread extends Thread {
	Lock lock;
	LinkedList<Integer> list;
	Boolean even;
	Condition con;
	Integer sum = 0;
	volatile Boolean done;
	
	public ConsumerThread(LinkedList<Integer> list, Lock lock, Boolean even, Condition con, Boolean done) {
		this.list=list;
		this.lock=lock;
		this.even = even;
		this.con=con;
		this.done=done;
	}
	
	
	public Boolean getDone() {
		return done;
	}


	public void setDone(Boolean done) {
		this.done = done;
	}


	@Override
	public void run() {
		while (this.done==false) {
			while (list.isEmpty()) {
				try {
					con.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			lock.lock();
			
			Integer got;
			try {
				got = list.removeLast();
			} finally {
				lock.unlock();
			}
			if (even && ((got %2) == 0)) {
//				this.sum += list.removeLast();
				this.sum++;
			}
			else {
//				this.sum+=list.removeLast();
				this.sum++;
			}
		}
	}
}
