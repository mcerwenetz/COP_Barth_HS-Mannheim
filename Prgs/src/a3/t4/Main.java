package a3.t4;

public class Main {

	public static void main(String[] args) {
		
		MyExecutor myExecutor = new MyExecutor(4);
		
		Runnable r = new Runnable() {
			public void run() {
				System.out.println("running");
			}
		};
		
		for (int i = 0; i < 1000; i++) {
			myExecutor.execute(r);
		}
		System.out.println("Limit reached " + myExecutor.limitReached + " times");
	}

}
