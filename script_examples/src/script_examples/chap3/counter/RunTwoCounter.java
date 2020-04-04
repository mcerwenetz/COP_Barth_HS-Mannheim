package script_examples.chap3.counter;

import script_examples.Util;

public class RunTwoCounter {
	final static int RUNS = 100_000;
	//unsafe
	static Counter counter = new TwoCounter();
	
	//safe
	//static TwoCounterSafe counter = new TwoCounterSafe();
	static int diffs =0;
	
	static Runnable peekeRunnable = () ->{
		// Nicht so genau. diffs können auch hochgezählt werden weil counter
		// noch in einer alten Iteration feststeckt.
		for (int i = 0; i < RUNS; i++) {
			if(counter.get() %2 == 1) {
				diffs++;
			}
		}
	};
	
	public static void main(String[] args) {
		RunCounter runCounter = new RunCounter();
		Thread tpeakerThread = new Thread(peekeRunnable);
		tpeakerThread.start();
		Util.resetTime();
		runCounter.run(counter);
		long time = Util.getTimeMilis();
		System.out.println("took " + time + "ms");
		Util.join(tpeakerThread);
		
		//With unsafe counters beneath but they are synronized so still savw
		counter = new TwoCounterSafeEff();
		tpeakerThread = new Thread(peekeRunnable);
		tpeakerThread.start();
		Util.resetTime();
		runCounter.run(counter);
		time = Util.getTimeMilis();
		System.out.println("took " + time + "ms");
	}

}
