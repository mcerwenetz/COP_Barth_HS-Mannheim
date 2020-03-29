package a1.t2;

public class IntSafeStateThread extends Thread {
	
	public static IntSafeState safe = new IntSafeState();
	private int inc_to=100;
	
	@Override
	public void run() {
		for(int i=0; i < inc_to; i++) {
			safe.inc(1);
		}
	}
}
