package script_examples.chap3.lock_types;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExplicit {
	
	private ReentrantLock lock = new ReentrantLock();
	
	
	public void f() {
		lock.lock();
		System.out.println("f\n");
		g();
		lock.unlock();
	}
	
	public void g() {
		lock.lock();
		System.out.println("g\n");
		lock.unlock();
	}
	
	public static void main(String[] args) {
		ReentrantExplicit reentrant = new ReentrantExplicit();
		reentrant.g();
		reentrant.f();
	}
}
