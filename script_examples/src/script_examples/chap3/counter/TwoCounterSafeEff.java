package script_examples.chap3.counter;

public class TwoCounterSafeEff implements Counter {

	private Counter c1;
	private Counter c2;

	
	public TwoCounterSafeEff() {
		//Two unsafe counters which are increased by a syncronized method are safe again
		c1 = new UnsafeCounter();
		c2 = new UnsafeCounter();
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
