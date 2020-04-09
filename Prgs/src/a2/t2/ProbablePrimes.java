package a2.t2;

class ProbablePrime {

	public static void main(String[] args) {
		PrimeCounterThread primeCounterThread = new PrimeCounterThread();
		primeCounterThread.start();
		try {
			primeCounterThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
