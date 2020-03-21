package script_example.chap3.primegen;

public class RunIthPrimeMany {
	 public static void main(String[] args) throws InterruptedException {
		int limit = 10000;
		int[] is = {limit, limit+1, limit+2};
		IthPrime[] ith = {null,null,null};
		for(int j=0; j < is.length; j++) {
			ith[j] = new IthPrime(is[j]);
		}
		
		for(IthPrime t: ith) {
			t.start();
		}
		for(IthPrime t : ith) {
			t.join();
			int p=t.getIthPrime();
			//Non deterministic outout
			System.out.println(p);
		}
	}
}
