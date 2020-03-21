package script_examples.chap3.hello;

public class HelloVerbose implements Runnable {

	@Override
	public void run() {
		System.out.println("Hello Java");

	}

	public static void main(String[] args) {
		Runnable doit = new HelloVerbose();
		doit.run();

	}

}
