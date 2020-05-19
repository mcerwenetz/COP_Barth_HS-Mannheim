package script_examples.chap7.snopf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import script_examples.Util;
import script_examples.chap7.snopf.Primes;

public class Main {
	public static void main(String[] args) {
		List<BigInteger> ns = new ArrayList<BigInteger>();
		int start=1_000_000;
		int no = 1000;
		for (int i = start; i < start+no; i++) {
			String sval= Integer.valueOf(i).toString();
			ns.add(new BigInteger(sval));
		}
		
		Util.resetTime();
		BigInteger snopf = Primes.seqSnopf(ns);
		long used = Util.getTimeMilis();
		System.out.println("Sequenzielles Snopf braucht " + used + "ms");
		
		Util.resetTime();
		BigInteger parsnopf = Primes.parSNOPF(ns);
		used = Util.getTimeMilis();
		System.out.println("Paralelles Snopf braucht " + used + "ms");
	}
}
