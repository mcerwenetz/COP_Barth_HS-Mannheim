package script_examples.chap6;
//https://www.tutorialspoint.com/CountDownLatch-in-Java
import java.util.concurrent.CountDownLatch;

public class Application implements Runnable {
	private String name;
	private CountDownLatch countDownLatch;
	
	public Application(String name, CountDownLatch countDownLatch) {
		super();
		this.name = name;
		this.countDownLatch = countDownLatch;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(name + " is ready");
			countDownLatch.countDown();
			countDownLatch.await();
			System.out.println(name + " runs");
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		countDownLatch.countDown();
	}
}
