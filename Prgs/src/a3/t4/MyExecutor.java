package a3.t4;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class MyExecutor implements Executor {

	Integer maxThreads;
	AtomicInteger runningThreads;
	Integer limitReached=0;
	private Boolean doBlock;

	public MyExecutor(Integer maxThreads) {
		this.maxThreads = maxThreads;
		this.runningThreads = new AtomicInteger(0);
		doBlock=true;
	}
	
	public void doBlock(Boolean block) {
		this.doBlock = block;
	}

	@Override
	public void execute(Runnable command) {
		if (runningThreads.get() >= maxThreads) {
			if(doBlock) {
//			System.out.println("Queue full");
			limitReached++;
			return;
			}
			else {
				throw new RejectedExecutionException();
			}
		} else {
			Thread t = new Thread(command);
			t.start();
			runningThreads.incrementAndGet();
			Runnable r = () -> {
				try {
					t.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				runningThreads.decrementAndGet();
			};
			Thread finisher = new Thread(r);
			finisher.start();
		}

	}
}
