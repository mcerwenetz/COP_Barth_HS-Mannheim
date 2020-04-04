package script_examples.chap3.counter;

import script_examples.Util;

public class RunCounterTest {

	public static void run() {
		//Counter volCounter = new UnsafeCounterVolatile();
		//Counter atomCounter = new SafeCounterAtomic();
		//eigentlich unsicher
		Counter twoCounter = new TwoCounter();
		RunCounter runC = new RunCounter(10_000_000, 4);
		Util.resetTime();
		long count = runC.run(twoCounter);
		long millis = Util.getTimeMilis();
		System.out.println(count + " " + millis);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 7; i++) {
			run();
		}
	}
	
}

