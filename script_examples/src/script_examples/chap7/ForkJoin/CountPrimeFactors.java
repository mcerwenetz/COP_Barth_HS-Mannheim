package script_examples.chap7.ForkJoin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;



public class CountPrimeFactors extends  RecursiveTask<Integer>{
	//static ExecutorService es = Executors.newFixedThreadPool(2);
	//neuer ForkJoinPool erlaubt rekursives aufrufen.
	//Argument ist paralellism. 2 Threads dürfen gleichzeitig laufen. Alle anderen werden pausiert
	ForkJoinPool forkJoinPool = new ForkJoinPool(2);
	private List<BigInteger> ns;
	
	public CountPrimeFactors(List<BigInteger> ns) {
		this.ns=ns;
	}
	
	@Override
	//Neue Methode compute statt call
	public Integer compute() {
		if (ns.isEmpty()) {
			return 0;
		} else {
			//Erstes Element von ns entnehmen
			BigInteger head = ns.get(0);
			//Erstes Element von ns löschen.
			ns.remove(0);
			//Anzahl der Primfaktoren für den head berechnen
			int headCount = Primes.primeFactors(head).size();
			//neues CountPrimeFactors Callable
			CountPrimeFactors rec = new CountPrimeFactors(ns);
			//invokeAll wartet biss alle computes aller Tasks gelaufen sind
			invokeAll(rec);
			//join gibt hier das resultat von compute zurück sobald eins vorliegt
			return headCount + rec.join();
		}
	}
	
	public static void main(String[] args) {
		List<BigInteger> ns = new ArrayList<>();
		for (Integer i = 1_000_000; i < 1_000_000 + 1000 ; i++) {
			ns.add(new BigInteger(i.toString()));
		}
		Integer result = null;
		CountPrimeFactors count = new CountPrimeFactors(ns);
		//Run the first task (run all others recursivly)
		count.forkJoinPool.invoke(count);
		//Warte wird beschrieben sobald count auch wirklich mit allen fertig ist.
		result = count.join();
		//Ausgabe
		System.out.println(result);
	}

}
