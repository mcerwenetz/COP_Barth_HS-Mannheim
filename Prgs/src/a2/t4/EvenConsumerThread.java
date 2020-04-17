package a2.t4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;

import util.Sum;

public class EvenConsumerThread extends Thread {

	
	Boolean conDone=false;
	BlockingQueue<Integer> evenConQueue;
	Lock evenLock;
	Sum sum;
	
	public EvenConsumerThread(BlockingQueue<Integer> evenConQueue, Lock evenLock, Sum sum) {
		super();
		this.evenConQueue = evenConQueue;
		this.evenLock = evenLock;
		this.sum=sum;
	}
	
	@Override
	public void run() {
		while (!conDone) {
			try {
				evenLock.lock();
				sum.increaseBy(evenConQueue.remove());
			} finally {
				evenLock.unlock();
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
