package script_examples.chap7;

public class MyThread extends Thread {

	public MyThread(Runnable r, String s) {
		super(r,s);
	}
	
	@Override
	public void run() {
		String n = getName();
		System.out.println(n);
		super.run();
		System.out.println("end of " + n);
	}
}
