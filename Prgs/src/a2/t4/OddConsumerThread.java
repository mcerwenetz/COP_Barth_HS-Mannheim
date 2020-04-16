package a2.t4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;

import util.Sum;

public class OddConsumerThread extends Thread {
	
	Boolean conDone=false;

	BlockingQueue<Integer> oddConQueue;
	Lock oddLock;
	Sum sum;
	
	
	public OddConsumerThread(BlockingQueue<Integer> oddConQueue, Lock oddLock, Sum sum) {
		super();
		this.oddConQueue = oddConQueue;
		this.oddLock = oddLock;
		this.sum=sum;
	}



	@Override
	public void run() {
		while (!conDone) {
			try {
				oddLock.lock();
				if (oddConQueue.peek() != null) {
					sum.increaseBy(oddConQueue.remove());
				}
			} finally {
				oddLock.unlock();
			}
			
		}
		
	}
	
	public Boolean getConDone() {
		return conDone;
	}



	public void setConDone(Boolean conDone) {
		this.conDone = conDone;
	}


}
