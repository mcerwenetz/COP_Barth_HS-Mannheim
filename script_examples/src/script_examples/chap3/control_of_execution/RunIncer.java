package script_examples.chap3.control_of_execution;

import script_examples.Util;

public class RunIncer {

	public static void main(String[] args) {
		Incer incer = new Incer();
		incer.start();
		Util.sleep(3000);
		incer.quit();
		boolean alive = incer.isAlive();
		System.out.println("Wanted to quit. Still alive?: "+ alive);
		Util.join(incer);
		alive = incer.isAlive();
		System.out.println("Joined and signaled wanted to quit. Still alive?: " + alive);

	}

}
