package script_examples.chap3.callbacks;

public class MainClass {
	public static void main(String[] args) {
		Callback callback = new Callback() {
			
			@Override
			public void callback() {
				System.out.println("Callback");
			}
		};
		
		new Thread(()->{
			callback.callback();
		}).start();
	}
}
