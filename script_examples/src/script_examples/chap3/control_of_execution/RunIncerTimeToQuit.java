package script_examples.chap3.control_of_execution;

import script_examples.Util;

public class RunIncerTimeToQuit {

	public static void main(String[] args) {
		Incer incer = new Incer();
		incer.start();
		Util.sleep(3000);
		Util.resetTime();
		incer.quit();
		Util.join(incer);
		long quit = Util.getTimeMilis();
		System.out.println("milis: " + quit);
		
		
		IncerInterrupt ii = new IncerInterrupt();
		ii.start();
		Util.sleep(3000);
		Util.resetTime();
		ii.quit();
		Util.join(ii);
		quit = Util.getTimeMilis();
		System.out.println("milis: " + quit);

	}

}
