package script_examples.chap3.longrunning;

import script_examples.chap3.primegen.PrimeGen;

public class LongRunningCallback extends Thread {
	private int ith, prime;
	private UsePrime whenDone;
	
	public LongRunningCallback(int ith, UsePrime whendone) {
		this.ith=ith;
		this.whenDone=whendone;
	}
	
	public void run() {
		PrimeGen p = new PrimeGen();
			while(ith-- > 0) {
				prime = p.next();
			}
		if(whenDone != null) {
			whenDone.usePrime(prime);
		}
	}
}
