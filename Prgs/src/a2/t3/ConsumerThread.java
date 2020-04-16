package a2.t3;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ConsumerThread extends Thread {
	Lock lock;
	LinkedList<Integer> list;
	Boolean even;
	Condition con;
	Integer sum = 0;
	Boolean done=false;

	public ConsumerThread(LinkedList<Integer> list, Lock lock, Boolean even, Condition con) {
		this.list=list;
		this.lock=lock;
		this.even = even;
		this.con=con;
	}


	public Boolean getDone() {
		return done;
	}


	public void setDone(Boolean done) {
		this.done = done;
	}


	@Override
	public void run() {
		Integer got=0;
		while (!done) {
			try {
				lock.lock();
				if(list.size() != 0) {
					got = list.remove();
					if (even && got %2 == 0) {
						this.sum += got;
					}
					else {
						this.sum += got;					}
				}
				con.await(100, TimeUnit.MICROSECONDS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}



		}
	}
}
