package script_examples.chap7.snopf;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import script_examples.Util;

public class Main {
	public static void main(String[] args) {
		List<BigInteger> ns = new ArrayList<BigInteger>();
		int start=1_000_000;
		int no = 100;
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
		
		Util.resetTime();
		BigInteger seqWithBound =Primes.seqBoundSnopf(ns);
		used=Util.getTimeMilis();
		System.out.println("Sequenzielles mit bound braucht " + used + "ms");
		
		Util.resetTime();
		BigInteger parrBoundSNOPF =Primes.parrBoundSNOPF(ns);
		used=Util.getTimeMilis();
		System.out.println("Paralelles mit bound braucht " + used + "ms");
	}
}
