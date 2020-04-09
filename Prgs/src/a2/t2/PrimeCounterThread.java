package a2.t2;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;

public class PrimeCounterThread extends Thread {

	BigInteger lowerlimit;
	BigInteger upperlimit;
	BigInteger[] zaehler;

	Lock lock;
	Integer[] primes;

	public PrimeCounterThread(BigInteger ll, BigInteger ul, Lock lock, BigInteger[] zaehler, Integer[] primes) {
		this.lowerlimit = ll;
		this.upperlimit = ul;
		this.zaehler = zaehler;
		this.lock = lock;
		this.primes = primes;
	}

	@Override
	public void run() {
		while (zaehler[0].doubleValue() <= upperlimit.doubleValue()) {
			try {
				lock.lock();
				if (zaehler[0].isProbablePrime(1)) {
					primes[0]++;

				}
				zaehler[0] = zaehler[0].add(BigInteger.ONE);
			}
			finally {
				lock.unlock();
			}
		}
	}
}
