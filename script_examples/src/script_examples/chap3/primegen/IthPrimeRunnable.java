package script_examples.chap3.primegen;

public class IthPrimeRunnable extends Thread implements Runnable {
	private int i;
	private int ithPrime;
	
	public IthPrimeRunnable(int i) {
		this.i=i;
	}
	
	@Override
	public void run() {
		int j = i;
		PrimeGen primeGen = new PrimeGen();
		while(j-- > 0) {
			//primegen.next returnes the prime for the number of times 
			//it has been called
			//For example if I want to know the 3th prime which is five
			// next() must be called 3 times
			ithPrime = primeGen.next();
		}
		System.out.println("Done");
	}
	
	public int getIthPrime(){
		return ithPrime;
	}
}
