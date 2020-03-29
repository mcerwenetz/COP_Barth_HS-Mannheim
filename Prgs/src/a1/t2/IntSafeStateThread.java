package a1.t2;

public class IntSafeStateThread extends Thread {
	
	public static IntSafeState safe = new IntSafeState();
	public static int inc_to=IntUnsafeThread.inc_to;
	
	@Override
	public void run() {
		for(int i=0; i < inc_to; i++) {
			safe.inc(1);
		}
	}
}
