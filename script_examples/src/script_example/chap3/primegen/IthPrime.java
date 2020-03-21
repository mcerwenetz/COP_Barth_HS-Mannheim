package script_example.chap3.primegen;

public class IthPrime extends Thread {

	private int i;
	private int ithPrime;
	
	public IthPrime(int i) {
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
	
	public static void main(String[] args) {
		IthPrime i = new IthPrime(3);
		i.run();
		System.out.println(i.getIthPrime());
	}
	
}
