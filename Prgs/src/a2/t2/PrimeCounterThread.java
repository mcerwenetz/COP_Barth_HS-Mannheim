package a2.t2;

import java.util.concurrent.locks.Lock;

public class PrimeCounterThread extends Thread {

	BigIntHolder upperlimit;
	BigIntHolder zaehler;
	IntHolder primes;
	
	
	Lock lock;

	public PrimeCounterThread(BigIntHolder upperlimit, BigIntHolder zaehler, IntHolder primes, Lock lock) {
		this.upperlimit = upperlimit;
		this.zaehler=zaehler;
		this.primes = primes;
		this.lock = lock;
	}

	@Override
	public void run() {
		while (zaehler.getB().doubleValue() <= upperlimit.getB().doubleValue()) {
			try {
				lock.lock();
				if (zaehler.getB().isProbablePrime(1)) {
					primes.plusplus();;

				}
				zaehler.plusplus();
			} finally {
				lock.unlock();
			}
		}
	}
}
