package script_examples.chap3.bounded_incer;

import script_examples.Util;

public class BoundedIncer extends Thread {
	int bound;
	public BoundedIncer(int bound) {
		this.bound=bound;
	}
	
	public void run() {
		int x = 0;
		while (x<bound) {
			x++;
			Util.sleep(1000);
			System.out.println(x);
		}
	}
}
