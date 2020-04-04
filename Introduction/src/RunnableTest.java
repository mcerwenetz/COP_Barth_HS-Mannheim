
public class RunnableTest {
	
	static Runnable t2 = () -> {
		System.out.println("t2 running");
	};
	
	public static void main(String[] args) {
		Thread tr1 = new Thread(t2);
		tr1.start();
		try {
			tr1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Thread tr2 = new Thread(() -> {
			System.out.println("t1 running");
		});
		tr2.start();
		try {
			tr2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
