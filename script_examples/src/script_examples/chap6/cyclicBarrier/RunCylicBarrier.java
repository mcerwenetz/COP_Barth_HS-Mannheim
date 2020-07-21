package script_examples.chap6.cyclicBarrier;

import java.util.concurrent.atomic.AtomicInteger;

public class RunCylicBarrier {
	static final Waiter[] WAITERS = new Waiter[10];
	static AtomicInteger count = new AtomicInteger();
	
	public static void main(String[] args) {
		for (int i =0; i < WAITERS.length; i++) {
			WAITERS[i] = new Waiter();
		}
		for (Waiter waiter : WAITERS) {
			waiter.start();
		}
	}

}
