package a2.t2;

import java.util.concurrent.Semaphore;

public class MySemaphore {
	Semaphore lock;

	public MySemaphore(int permits) {
		lock = new Semaphore(permits);
	}

	public void aquire() {
		try {
			lock.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void acquire(int permits) {
		try {
			lock.acquire(permits);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void release() {
		lock.release();
	}

}
