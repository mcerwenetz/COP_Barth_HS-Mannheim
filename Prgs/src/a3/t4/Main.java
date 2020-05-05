package a3.t4;

public class Main {

	public static void main(String[] args) {
		MyExecutor myExecutor = new MyExecutor(1);

		int i = 1_000_000;
		while (i-- > 0) {
			Runnable run = () -> {
//				System.out.println("running");
//				Util.sleep(1000);

			};
			myExecutor.execute(run);

		}

		myExecutor.myThreadPoolExecutor.shutdown();
		System.out.println("Queue limit reached " + myExecutor.queueWasFull + " times");
	}

}
