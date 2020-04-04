package script_examples.chap3.counter;

public class TwoCounterSafe implements Counter {
	private Counter c1;
	private Counter c2;
	
	public TwoCounterSafe() {
		c1 = new SafeCounter();
		c2 = new SafeCounter();
	}

	@Override
	public synchronized void inc() {
		c1.inc();
		c2.inc();
	}
	

	@Override
	public synchronized int get() {
		return c1.get() + c2.get();
	}

}
