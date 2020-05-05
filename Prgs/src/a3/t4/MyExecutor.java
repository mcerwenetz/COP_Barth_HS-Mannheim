package a3.t4;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class MyExecutor implements Executor {

	Integer numberOfThreads;
	List<Runnable> cachedRunnables = new LinkedList<Runnable>();
	ExecutorService ex;
	ExecutorCompletionService<Void> ecs;
	AtomicInteger atomicInteger = new AtomicInteger(0);

	public MyExecutor(Integer numberOfThreads) {
		this.numberOfThreads = numberOfThreads;
		ex = Executors.newFixedThreadPool(numberOfThreads);
		ecs = new ExecutorCompletionService<Void>(ex);
	}

	@Override
	public void execute(Runnable command) {
		if (this.cachedRunnables.size() > numberOfThreads) {
			System.out.println("Queue full");
			return;
		} else {
			cachedRunnables.add(command);
			Runnable myRunnable = () -> {
				cachedRunnables.remove(command);
				ecs.submit(command,null);
				System.out.println(command + "submitted");
				this.atomicInteger.incrementAndGet();
			};
			ecs.submit(myRunnable, null);
		}

	}

}
