package script_examples.chap7.ForkJoin;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;



public class CountPrimeFactors implements Callable<Integer> {
	static ExecutorService es = Executors.newFixedThreadPool(2);

	private List<BigInteger> ns;
	
	public CountPrimeFactors(List<BigInteger> ns) {
		this.ns=ns;
	}
	
	@Override
	public Integer call() throws Exception {
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
			//erstelle einen future-task mit dem callable (gleiches wie hier nur mit Liste ohne head-element)
			FutureTask<Integer> ft = new FutureTask<>(rec);
			//Gibt FutureTask an Executorservice
			es.submit(ft);
			//rekursives return
			return headCount + ft.get();
		}
	}
	
	public static void main(String[] args) {
		List<BigInteger> ns = new ArrayList<>();
		for (Integer i = 1_000_000; i < 1_000_000 + 1000 ; i++) {
			ns.add(new BigInteger(i.toString()));
		}
		Integer result = null;
		CountPrimeFactors count = new CountPrimeFactors(ns);
		try {
			result = count.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		CountPrimeFactors.es.shutdown();
	}

}
