package script_examples.chap6.cyclicBarrier;

public class InBetween implements Runnable {

	@Override
	public void run() {
		int longest =0;
		for(Waiter waiter : RunCylicBarrier.WAITERS) {
			int slept = waiter.getSlept();
			if (slept > longest) {
				longest=slept;
			}
		}
		RunCylicBarrier.count.addAndGet(1);
		if (longest <= Waiter.MAXSLEEP*2/3) {
			for(Waiter w: RunCylicBarrier.WAITERS) {
				w.shutDown();
			}
		}
		System.out.println("In Between");
	}

}
