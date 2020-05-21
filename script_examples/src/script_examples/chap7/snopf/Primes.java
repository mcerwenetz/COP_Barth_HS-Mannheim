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
		final Map<BigInteger, Integer> sizes = new ConcurrentHashMap<BigInteger, Integer>();
		final ExecutorService es = Executors.newFixedThreadPool(16);
		BigInteger minn = ns.get(0);
		int initsize = primeFactors(minn).size();
		final AtomicInteger bound = new AtomicInteger(initsize);
		for ( final BigInteger n : ns) {
			es.execute(() -> {
				int size =primeFactors(n, bound.get()).size();
				sizes.put(n, size);				
				int currentBound = bound.get();
				while (size < currentBound) {
					bound.compareAndSet(currentBound, size);
					currentBound = bound.get();
				}
			});
		}
		joinExecutor(es);
		return keyMinValue(sizes);
	}
	
}
