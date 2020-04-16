package a2.t3;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import util.Sum;

public class EvenConsumerThread extends ConsumerThread {
	
	Sum sum;

	public EvenConsumerThread(LinkedList<Integer> list, Lock lock, Condition con, Sum sum) {
		super(list, lock, con);
		// TODO Auto-generated constructor stub
		
		this.sum=sum;
	}
	
	@Override
	public void run() {
		Integer got=0;
		while (!done) {
			try {
				lock.lock();
				if(list.size() != 0) {
					got=list.remove();
					if (got%2==0) {
						sum.increaseBy(got);
					}else {
						list.add(got);
					}
				}else {
					con.await(100, TimeUnit.MICROSECONDS);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

}
