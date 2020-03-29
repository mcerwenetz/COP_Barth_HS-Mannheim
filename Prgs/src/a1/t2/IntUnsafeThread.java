package a1.t2;

public class IntUnsafeThread extends Thread {

	public static IntUnsafeState unsafe = new IntUnsafeState();
	private int inc_to=100;
	
	@Override
	public void run() {
		for(int i=0; i < inc_to; i++) {
			unsafe.inc(1);
		}
	}

}
