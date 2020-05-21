package script_examples.chap7.snopf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;



public class Primes {

	static List<BigInteger> primeFactors(BigInteger n){
		BigInteger i = new BigInteger("2");
		List<BigInteger> factors = new ArrayList<BigInteger>();
		while (n.compareTo(BigInteger.ONE)>0) {
			while (n.mod(i).equals(BigInteger.ZERO)) {
				n=n.divide(i);
				factors.add(i);
			}
			i=i.add(BigInteger.ONE);
		}
		return factors;
	}


	static BigInteger seqSnopf(List<BigInteger> ns) {
		Map<BigInteger, Integer> sizes = new ConcurrentHashMap<BigInteger, Integer>();
		for (BigInteger bigInteger : ns) {
			sizes.put(bigInteger, primeFactors(bigInteger).size());
		}
		return keyMinValue(sizes);
	}


	private static BigInteger keyMinValue(Map<BigInteger, Integer> sizes) {
		Set<BigInteger> ks = sizes.keySet();
		BigInteger minkey = ks.iterator().next();
		for(Map.Entry<BigInteger, Integer> entry : sizes.entrySet()) {
			Integer min = sizes.get(minkey);
			if(entry.getValue().compareTo(min) < 0) {
				minkey = entry.getKey();
			}
		}
		return minkey;
	}
	
	static BigInteger parSNOPF(List<BigInteger> ns) {
		final Map<BigInteger, Integer> sizes = new ConcurrentHashMap<BigInteger, Integer>();
		final ExecutorService es = Executors.newFixedThreadPool(16);
		for(final BigInteger n : ns) {
			es.execute(() -> {
				sizes.put(n, primeFactors(n).size());
			});
		}
		joinExecutor(es);
		return keyMinValue(sizes);
	}


	private static void joinExecutor(ExecutorService es) {
		es.shutdown();
		try {
			es.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	static BigInteger seqBoundSnopf(List<BigInteger> ns) {
		Map<BigInteger, Integer> sizes = new ConcurrentHashMap<BigInteger, Integer>();
		BigInteger minn = ns.get(0);
		int size = primeFactors(minn).size();
		AtomicInteger bound = new AtomicInteger(size);
		for (BigInteger n : ns) {
			size =primeFactors(n, bound.get()).size();
			sizes.put(n, size);
			if(size < bound.get()) {
				bound.set(size);
			}
		}
		return keyMinValue(sizes);
	}


	private static List<BigInteger> primeFactors(BigInteger n, int bound) {
		BigInteger i = new BigInteger("2");
		List<BigInteger> factors = new ArrayList<BigInteger>();
		while (n.compareTo(BigInteger.ONE) > 0) {
			while (n.mod(i).equals(BigInteger.ZERO)) {
				n=n.divide(i);
				factors.add(i);
				if(factors.size() >= bound) {
					factors.add(BigInteger.ZERO);
					return factors;
				}
			}
			i = i.add(BigInteger.ONE);
		}
		return factors;
	}
	
	
	static BigInteger parrBoundSNOPF(List<BigInteger> ns) {
		//ns ist die Liste an BigIntegern für die die Anzahl der Faktoren bestimmt werden soll.
		//der mit den wenigsten Faktoren wird zurückgegeben
		//sizes ist die Map in der Zahlen und deren Faktoranzahl drauf gemapped wird.
		final Map<BigInteger, Integer> sizes = new ConcurrentHashMap<BigInteger, Integer>();
		//ExecutorService für die Tasks später.
		final ExecutorService es = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		//minh ist das Erste Element der mitgegebenen Liste an Zahlen
		BigInteger minn = ns.get(0);
		//für die erste Zahl wird die Size ermittelt, damit die bound gesetzt werden kann.
		//Da hier noch keine Bound steht erfolgt auch der Aufruf noch ohne Bound
		int initsize = primeFactors(minn).size();
		//Bound wird erstellt. Atomic weil Sie später im Task gebraucht wird.
		final AtomicInteger bound = new AtomicInteger(initsize);
		//Schleife über alle Zahlen in der Liste. Paralell mit executorService damit
		//mehrere gleichzeitig drauf zugreifen können. ns wird nur gelesen deswegen kein Atomic.
		//sizes ist ne ConcurrentHashMap und bringt wechselseitigen Ausschluss schon mit.
		for ( final BigInteger n : ns) {
			es.execute(() -> {
				//size ist die Anzahl an Primfaktoren. Hier wird die Bound jetzt benutzt
				//In primeFactors wird pro Iteration abgefragt ob die Anzahl der Faktoren schon bound exceeded.
				//falls ja heißt das ja dass die Zahl schonmal nicht die wenigste Anzahl Faktoren hat.
				int size =primeFactors(n, bound.get()).size();
				//Size in hasmap eintragen.
				sizes.put(n, size);
				int currentBound = bound.get();
				//Falls die Bound größer ist als die größe des aktuellen Elements gibt es ein neues Minimum.
				while (size < currentBound) {
					//So lange auf änderung spinnen bis Sie erfolgt ist.
					bound.compareAndSet(currentBound, size);
					currentBound = bound.get();
				}
			});
		}
		joinExecutor(es);
		return keyMinValue(sizes);
	}
	
}
