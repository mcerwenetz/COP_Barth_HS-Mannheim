package script_examples.chap7.threadPool;

import java.util.concurrent.Executor;

public class WithinThreadExecutor implements Executor {

	@Override
	public void execute(Runnable command) {
		command.run();
	}

	public static void main(String[] args) {
		Executor executor = new WithinThreadExecutor();
		
		Runnable task = new Runnable() {

			@Override
			public void run() {
				System.out.println("Hello from Runnable");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ignored) {
				}
			}
		};
		
		executor.execute(task);
	}

}
