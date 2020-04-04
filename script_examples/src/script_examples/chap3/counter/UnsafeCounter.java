package script_examples.chap3.counter;

public class UnsafeCounter implements Counter {
	private int counter;
	public UnsafeCounter() {
		counter = 0;
	}
	@Override
	public void inc() {
		counter++;
	}
	@Override
	public int get() {
		return counter;
	}
	
	
}
