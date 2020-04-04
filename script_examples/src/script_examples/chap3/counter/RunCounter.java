package script_examples.chap3.counter;


public class RunCounter {

	private int noTimes = 10_000_000;
	private int noIncer=2;
	
	public RunCounter() {}
	
	public RunCounter(int noTimes,int noIncer) {
		this.noTimes=noTimes;
		this.noIncer=noIncer;
	}
	
	public int run(Counter counter) {
		Thread[] threads = new Thread[noIncer];
		for (int i = 0; i < threads.length; i++) {
			threads[i]=new CounterIncer(counter,noTimes);
		}
		for(Thread t : threads) {
			t.start();
		}
		for(Thread t : threads) {
			script_examples.Util.join(t);
		}
		return counter.get();
	}
	
}