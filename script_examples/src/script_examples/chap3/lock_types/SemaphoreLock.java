package script_examples.chap3.lock_types;

import java.util.concurrent.Semaphore;

public class SemaphoreLock {

	private Semaphore lockSemaphore = new Semaphore(1);
	
	public void f() {
		try {
			lockSemaphore.acquire();
			System.out.println("f\n");
			g();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lockSemaphore.release();
		}

	}
	
	public void g() {
		try {
			lockSemaphore.acquire();
			System.out.println("g\n");
			g();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lockSemaphore.release();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		SemaphoreLock classicLock = new SemaphoreLock();
		classicLock.g();
		classicLock.f();
	}
	
	
}
