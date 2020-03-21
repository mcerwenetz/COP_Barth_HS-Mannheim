package script_examples.chap3.bounded_incer;

public class RunBoundedIncerBad {

	public static void main(String[] args) {
		BoundedIncer bi = new BoundedIncer(7);
		bi.start();
		System.out.println("Something useful");
		while(bi.isAlive()) {
			//wating
		}
		System.out.println("Done");

	}

}
