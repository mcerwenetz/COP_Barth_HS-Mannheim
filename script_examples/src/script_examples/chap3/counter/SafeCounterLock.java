package script_examples.chap3.counter;

public class SafeCounterLock implements Counter {

	private int counter;
	private Object lock;
	
	public SafeCounterLock() {
		counter=0;
		lock  = new Object();
	}
	
	@Override
	public void inc() {
		synchronized (lock) {
			counter++;
		}
	}

	@Override
	public int get() {
		synchronized (lock) {
			return counter;
		}
	}

}
