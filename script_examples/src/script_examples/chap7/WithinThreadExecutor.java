package script_examples.chap7;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import script_examples.Util;

public class WithinThreadExecutor implements Executor {

	@Override
	public void execute(Runnable command) {
		command.run();
	}

	public static void main(String[] args) {
		Executor executor = new TPTExecutor();
		CountDownLatch latch = new CountDownLatch(4);
		Util.resetTime();

		for (int i = 4; i >= 1; i--) {
			final int j = i;
			Runnable cmd = () -> {
				System.out.println(j * 200 + " msecs");
				Util.sleep(j * 200);
				latch.countDown();
			};
			executor.execute(cmd);
		}
		try {
			latch.await();
		} catch (InterruptedException ignored) {
		}
		System.out.println(Util.getTimeMilis() + " msecs total");
	}

}
