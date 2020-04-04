package script_examples.chap3.lock_types;

public class Reentrant {
	
	public synchronized void f() {
		System.out.println("f\n");
		g();
	}
	
	public synchronized void g() {
		System.out.println("g\n");
	}

	public static void main(String[] args) {
		Reentrant reentrant = new Reentrant();
		reentrant.g();
		reentrant.f();

	}

}
