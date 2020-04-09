package a2.t2;

import java.math.BigInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import util.Util;

class ProbablePrime {

	public static void main(String[] args) {
		// zaehler für die einzelnen chargen an Threads
		BigInteger chargenzaehler = new BigInteger("0");
		// Upperlimit der primetests
		BigIntHolder upperlimit = new BigIntHolder(BigInteger.TEN.pow(5));
		// Anzahl an Primzahlen in eigenem Datenformat damit es geteilt werden kann
		IntHolder primes = new IntHolder(0);
		// lock damit nicht gleichzeitig auf primes zugegriffen werden kann
		Lock lock = new ReentrantLock();
		// cores sind die Anzahl der Prozessorkerne. Auf dem surface sind das 8. So groß
		// werden die einzelnen Thread-Chargen
		Integer cores = Runtime.getRuntime().availableProcessors();
		/*
		 * Semaphore. Es können maximal cores Threads erstellt werden. Unnötig wenn man
		 * in chargen arbeitet die so groß sind wie das System cores hat. Wenn cores
		 * heißt wie viele Threads gleichzeitig starten können. Die Semaphore ist der
		 * Flaschenhals
		 */
		MySemaphore sem = new MySemaphore(cores);
		Integer chargengroesse = 100;

		// Threadarray ist cores groß. Also eine Charge.
		Thread[] primeThreads = new Thread[chargengroesse];

		// solange der Zaehler das Limit nicht erreicht hat wird er inkrementiert um
		// cores (8) pro Charge
		while (chargenzaehler.intValue() < upperlimit.getB().intValue()) {
			// lokale Zählvariable = chargenzaehler. Für alle Slots im Array wird ein Thread
			// erstellt.
			for (BigInteger i = chargenzaehler; i.doubleValue() < chargenzaehler.intValue() + chargengroesse; i = i
					.add(BigInteger.ONE)) {
				// pos ist modulo damit es keinen overflow gibt. Sonst i ziemlich groß und
				// findet die pos im Array nicht mehr.
				primeThreads[i.intValue() % chargengroesse] = new PrimeCounterThreadSingle(i, primes, lock, sem);
			}
			// Alle Threads in einer charge starten
			for (var i : primeThreads) {
				i.start();
			}
			// Alle Threads in einer charge joinen
			for (var i : primeThreads) {
				Util.join(i);
			}
			// zaehler um charge erhöhen
			chargenzaehler = chargenzaehler.add(new BigInteger(chargengroesse.toString()));
		}
		System.out.println(primes);
	}

}
