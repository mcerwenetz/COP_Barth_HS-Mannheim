package a2.t2;

import java.math.BigInteger;

public class PrimeCounterThread extends Thread {

//	BigInteger lowerlimit = BigInteger.TEN.pow(1000);
//	BigInteger upperlimit = lowerlimit.add(BigInteger.TEN.pow(3));
	BigInteger lowerlimit = BigInteger.ONE;
	BigInteger upperlimit = BigInteger.TEN;


	int primes;

	@Override
	public void run() {

		for (BigInteger i = lowerlimit; i.doubleValue() < upperlimit.doubleValue(); i=i.add(BigInteger.ONE)) {
			if (i.isProbablePrime(2)) {
				primes++;
			}
		}
		System.out.println(primes);
	}
}
