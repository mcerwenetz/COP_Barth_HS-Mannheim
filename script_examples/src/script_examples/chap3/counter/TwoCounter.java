package script_examples.chap3.counter;

public class TwoCounter implements Counter {
	
	private Counter counter1;
	private Counter counter2;

	
	public TwoCounter() {
		counter1 = new SafeCounter();
		counter2 = new SafeCounter();
	}


	@Override
	public void inc() {
		counter1.inc();
		counter2.inc();
	}

	@Override
	public int get() {
		return counter1.get() + counter2.get();
	}

}
