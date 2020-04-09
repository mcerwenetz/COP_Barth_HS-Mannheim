package a2.t2;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;

public class PrimeCounterThreadSingle extends Thread {
	
	BigInteger num;
	IntHolder primes;
	Lock lock;
	MySemaphore semaphore;
	
	public PrimeCounterThreadSingle(BigInteger i, IntHolder primes, Lock lock, MySemaphore sem) {
		this.num=i;
		this.primes=primes;
		this.lock=lock;
		this.semaphore=sem;
	}
	
	@Override
	public void run() {
		try {
			semaphore.acquire(1);
			try {
				lock.lock();
				if (num.isProbablePrime(1)) {
					primes.plusplus();
				}
			} finally {
				lock.unlock();
			}
		} catch (Exception e) {
		}finally {
			semaphore.release();
		}
		
	}

}
