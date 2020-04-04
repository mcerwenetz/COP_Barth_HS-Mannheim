package script_examples.chap3.counter;

public class CounterIncer extends Thread {
	private Counter counter;
	private int times;
	
	public CounterIncer(Counter counter, int times) {
		this.counter=counter;
		this.times=times;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < times; i++) {
			counter.inc();
		}
	}
}
