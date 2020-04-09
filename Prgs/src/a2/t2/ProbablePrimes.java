package a2.t2;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.Util;

class ProbablePrime {

	public static void main(String[] args) {
		BigIntHolder zaehler = new BigIntHolder(new BigInteger("1"));
		BigIntHolder upperlimit = new BigIntHolder(BigInteger.TEN.pow(8));
		IntHolder primes = new IntHolder(0);
		int threadcount = upperlimit.getB().intValue() - zaehler.getB().intValue();
		Lock lock = new ReentrantLock();
		MySemaphore sem = new MySemaphore(16);
		
		Thread[] primeThreads = new Thread[threadcount];
		
		
		for (BigInteger i = BigInteger.ZERO; i.doubleValue() < primeThreads.length; i=i.add(BigInteger.ONE)) {
			primeThreads[i.intValue()] = new PrimeCounterThreadSingle(i, primes, lock, sem);
		}

		for (var i : primeThreads) {
			i.start();
		}

		for (var i : primeThreads) {
			Util.join(i);
		}
		
		System.out.println(primes);
	}

}
