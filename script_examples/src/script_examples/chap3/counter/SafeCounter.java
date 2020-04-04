package script_examples.chap3.counter;

public class SafeCounter implements Counter {

	private int counter;
	
	public SafeCounter() {
		int counter = 0;
	}
	
	@Override
	public synchronized void inc() {
		counter++;

	}

	@Override
	public synchronized int get() {
		return counter;
	}

}
