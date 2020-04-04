package script_examples.chap3.counter;

import script_examples.Util;

public class RunSafeCounter {

	public static void run() {
		Counter safeCounter = new SafeCounter();
		RunCounter runC = new RunCounter();
		Util.resetTime();
		long count = runC.run(safeCounter);
		long millis = Util.getTimeMilis();
		System.out.println(count + " " + millis);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 7; i++) {
			run();
		}
	}
	
}
