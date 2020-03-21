package script_examples.chap3.longrunning;

import script_examples.chap3.primegen.PrimeGen;

public class LongRunning extends Thread {
	int ith;
	int prime;
	public LongRunning(int ith) {
		this.ith=ith;
	}
	
	public void run() {
		PrimeGen p = new PrimeGen();
		while(ith-- > 0) {
			prime = p.next();
		}
	}
	
	public int getPrime() {
		return prime;
	}
}
