package script_examples.chap7.snopf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


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
}
