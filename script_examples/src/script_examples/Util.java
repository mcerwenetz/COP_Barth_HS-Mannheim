package script_examples;
public class Util {
	private static long nanoSecs;
	public final static void resetTime() {
		nanoSecs = System.nanoTime();
	}
	
	public final static long getTimeMicros() {
		long current = System.nanoTime();
		return (current-nanoSecs)/1000;
	}
	
	public final static long getTimeMilis() {
		long current = System.nanoTime();
		return (current-nanoSecs)/1000000;
	}
}
