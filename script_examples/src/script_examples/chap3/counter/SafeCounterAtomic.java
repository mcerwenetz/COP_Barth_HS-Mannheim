package script_examples.chap3.counter;

import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounterAtomic implements Counter {
	private AtomicInteger counter;
	
	public SafeCounterAtomic() {
		counter = new AtomicInteger();
	}
	
	@Override
	public void inc() {
		counter.incrementAndGet();
	}

	@Override
	public int get() {
		return counter.intValue();
	}

}
