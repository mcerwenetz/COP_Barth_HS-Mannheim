package a1.t2;

public class IntUnsafeThread extends Thread {

	public static IntUnsafeState unsafe = new IntUnsafeState();
	public static int inc_to=1_000_000;
	
	@Override
	public void run() {
		for(int i=0; i < inc_to; i++) {
			unsafe.inc(1);
		}
	}

}
