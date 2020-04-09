package a2.t2;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.*;

class ProbablePrime {

	public static void main(String[] args) {
		BigInteger lowerlimit = BigInteger.ONE;
		BigInteger upperlimit = new BigInteger("100");
		BigInteger[] zaehler= {lowerlimit};
		
		int maxthreads = Runtime.getRuntime().availableProcessors();
//		int maxthreads = 2;
		Thread[] primeThreads = new Thread[maxthreads];
		Lock lock = new ReentrantLock();
		
		Integer[] primes = {0};
		
		for (int i = 0; i < primeThreads.length; i++) {
			primeThreads[i] = new PrimeCounterThread(lowerlimit,upperlimit,lock,zaehler,primes);
		}

		for (var i : primeThreads) {
			i.start();
		}

		for (var i : primeThreads) {
			Util.join(i);
		}
		System.out.println(primes[0]);
	}

}
