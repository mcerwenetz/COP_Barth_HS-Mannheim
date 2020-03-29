package a1.t2;

import java.util.LinkedList;
import java.util.List;

public class IntStateTest {
	


	public static void main(String[] args) {
		
		//Unsafe
		List<IntUnsafeThread> unsafeThreadList = new LinkedList<IntUnsafeThread>();
		for(int i=0; i <1000; i++) {
			IntUnsafeThread e = new IntUnsafeThread();
			unsafeThreadList.add(e);
		}
		for(Thread t : unsafeThreadList) {
			t.start();
		}
		for(Thread t: unsafeThreadList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(IntUnsafeThread.unsafe.getValue());
	
		//Safe
		List<IntSafeStateThread> safeThreadList = new LinkedList<IntSafeStateThread>();
		for(int i=0; i <1000; i++) {
			IntSafeStateThread e = new IntSafeStateThread();
			safeThreadList.add(e);
		}
		for(Thread t : safeThreadList) {
			t.start();
		}
		for(Thread t: safeThreadList) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(IntSafeStateThread.safe.getValue());
	}
}
