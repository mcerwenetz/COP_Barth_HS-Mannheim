package script_examples.chap3.hello;

public class Hello 
	implements Runnable {
		public void run() {
			System.out.println("Hello Java");
		}
	
	public static void main(String[] args) {
		new Hello().run();
	}
}
