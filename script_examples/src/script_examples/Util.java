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
	
	public final static void sleep(long millisecs) {
		try{
			Thread.sleep(millisecs);
		}catch(InterruptedException e) {
			System.out.println("Util.sleep: Interrupted");
		}
	}
	
	public final static void join(Thread d) {
		try {
			d.join();
		} catch (InterruptedException e) {
			System.out.println("Util.join: Interrupt joining");
		}
	}

	public static void joinall(Thread[] ts) {
		for (Thread thread : ts) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
