package script_examples.chap3.counter;

import script_examples.Util;

public class RunUnsafeCounter {
	public static void run() {
		Counter counter = new UnsafeCounter();
		RunCounter runC= new RunCounter();
		Util.resetTime();
		long count = runC.run(counter);
		long millis = Util.getTimeMilis();
		System.out.println(count + " " + millis);
		
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 7; i++) {
			run();
		}
	}

}
