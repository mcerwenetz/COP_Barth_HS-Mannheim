package script_examples.chap7.threadPool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import com.sun.jmx.mbeanserver.Util;

public class WithinThreadExecutor implements Executor {

	@Override
	public void execute(Runnable command) {
		command.run();
	}

	public static void main(String[] args) {
		Executor executor = new WithinThreadExecutor();

		int times = 10;

		CountDownLatch cl = new CountDownLatch(times);

		script_examples.Util.resetTime();
		
		for (int i = 0; i < times; i++) {
			Runnable task = new Runnable() {

				@Override
				public void run() {
//				System.out.println("Hello from Runnable");
					try {
						Thread.sleep(100);
					} catch (InterruptedException ignored) {
					}
					System.out.println("slept 100ms");
					cl.countDown();
				}
			};

			executor.execute(task);

		}

		try {
			cl.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Beendet");
		System.out.println("Took: " + script_examples.Util.getTimeMilis() +" ms");
	}

}
