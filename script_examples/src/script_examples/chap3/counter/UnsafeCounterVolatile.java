package script_examples.chap3.counter;

public class UnsafeCounterVolatile implements Counter {
	private volatile int counter;
	
	public UnsafeCounterVolatile() {
		counter=0;
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
