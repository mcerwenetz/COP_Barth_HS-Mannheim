package a1.t2;

import java.util.LinkedList;
import java.util.List;
import util.Util;

public class IntStateTest {
	


	public static void main(String[] args) {
		int threadcount = 1;
		int goal = threadcount*IntSafeStateThread.inc_to;
		//Unsafe
		List<IntUnsafeThread> unsafeThreadList = new LinkedList<IntUnsafeThread>();
		for(int i=0; i <threadcount; i++) {
			IntUnsafeThread e = new IntUnsafeThread();
			unsafeThreadList.add(e);
		}
		Util.resetTime();
		for(Thread t : unsafeThreadList) {
			t.start();
		}
		for(Thread t: unsafeThreadList) {
			Util.join(t);
		}
		long unsafeTime = Util.getTimeMilis();
		double ratio = (IntUnsafeThread.unsafe.getValue()/(double)goal);
		double error = 1.0-ratio;
		error*=100;
		error = Math.round(error*100)/100;
		System.out.println(IntUnsafeThread.unsafe.getValue() + " " + unsafeTime + "ms Error:" + error + "%");
	
		//Safe
		List<IntSafeStateThread> safeThreadList = new LinkedList<IntSafeStateThread>();
		Util.resetTime();
		for(int i=0; i <threadcount; i++) {
			IntSafeStateThread e = new IntSafeStateThread();
			safeThreadList.add(e);
		}
		for(Thread t : safeThreadList) {
			t.start();
		}
		for(Thread t: safeThreadList) {
			Util.join(t);
		}
		long safetime = Util.getTimeMilis();
		System.out.println(IntSafeStateThread.safe.getValue() + " " + safetime+"ms\nTiming difference: " + (unsafeTime-safetime)+ "ms");
	}
}
