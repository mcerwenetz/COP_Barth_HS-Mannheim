package script_examples.chap3.bounded_incer;

import script_examples.Util;

public class RunBoundedIncerOk {

	public static void main(String[] args) {
		BoundedIncer bi = new BoundedIncer(7);
		bi.start();
		//could take longer. Thread cannot join already
		System.out.println("Something useful");
		Util.join(bi);
		System.out.println("Done");
	}

}
