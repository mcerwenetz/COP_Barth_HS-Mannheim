package script_examples.chap3.lock_types;

public class ReentrantObject {
	private Object lockObject  = new Object();
	
	public void f() {
		synchronized (lockObject) {
			System.out.println("f\n");
			g();
		}
	}
	
	public void g() {
		synchronized (lockObject) {
			System.out.println("g\n");
		}
	}
	
	public static void main(String[] args) {
		ReentrantObject reentrantObject = new ReentrantObject();
		reentrantObject.g();
		reentrantObject.f();
	}
	
	
}
