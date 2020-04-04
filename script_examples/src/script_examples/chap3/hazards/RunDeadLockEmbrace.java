package script_examples.chap3.hazards;

import script_examples.Util;

public class RunDeadLockEmbrace {
	final static int RUNS = 10_000;
	
	
	public static void main(String[] args) {
		final DeadLockEmbrace d = new DeadLockEmbrace();
		
		Thread t1 = new Thread(()-> {
			for (int i = 0; i < RUNS; i++) {
				d.doIt12();
			}
		});
		
		Thread t2 = new Thread(()-> {
			for (int i = 0; i < RUNS; i++) {
				d.doIt21();
			}
		});
		t1.start();
		t2.start();
		Util.join(t2);
		Util.join(t1);
	}
}
