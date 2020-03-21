package script_example.chap3.primegen;

public class RunIthPrime {
	public static void main(String[] args) throws InterruptedException {
		int limit = 100;
		IthPrime i = new IthPrime(limit);
		i.start();
		System.out.println("Something useful");
		//join prevents finishing of main thread and waits for i to finish
		//else p would be just 0 because i could not finish
		i.join();
		int p = i.getIthPrime();
		System.out.println(p);
	}
}