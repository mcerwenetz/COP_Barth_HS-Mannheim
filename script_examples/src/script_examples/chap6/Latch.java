package script_examples.chap6;

import java.util.concurrent.CountDownLatch;

//https://www.tutorialspoint.com/CountDownLatch-in-Java
public class Latch {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(2);

		Thread app1 = new Thread(new Application("Excel", latch));
		Thread app2 = new Thread(new Application("Word", latch));

		app1.start();
		app2.start();

	}

}
