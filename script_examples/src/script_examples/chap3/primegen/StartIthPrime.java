package script_examples.chap3.primegen;

public class StartIthPrime {

	public static void main(String[] args) {
		int ithpos = 1000000;
		//Thread
		IthPrime i = new IthPrime(ithpos);
		i.start();
		
		//Runnable
		IthPrimeRunnable ir = new IthPrimeRunnable(ithpos);
		Thread t = new Thread(ir);
		t.start();

	}

}
