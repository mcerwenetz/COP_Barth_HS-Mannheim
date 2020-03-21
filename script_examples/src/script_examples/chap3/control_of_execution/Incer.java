package script_examples.chap3.control_of_execution;

import script_examples.Util;

public class Incer extends Thread {
	private volatile boolean doRun = true;

	@Override
	public void run() {
		int x = 0;
		while(doRun){
			x++;
			Util.sleep(1000);
			System.out.println(x);
		}
	}
	public void quit() {
		doRun=false;
	}
}
