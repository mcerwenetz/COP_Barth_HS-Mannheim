package script_examples.chap3.hazards;

import java.util.concurrent.Semaphore;

public class DeadLockPosix {

	Semaphore semaphore = new Semaphore(1);
	
	public void doit() throws InterruptedException {
		semaphore.acquire();
		System.out.println("doit");
		semaphore.release();
	}
	
	public void doDoit() throws InterruptedException {
		semaphore.acquire();
		System.out.println("doDoIt");
		doit();
		semaphore.release();
	}
	
	public static void main(String[] args) {
		DeadLockPosix deadLockPosix = new DeadLockPosix();
		try {
			deadLockPosix.doit();
			deadLockPosix.doDoit();
		} catch (InterruptedException e) {
		}
	}
	
}
