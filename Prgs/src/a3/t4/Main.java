package a3.t4;
import util.*;

public class Main {

	public static void main(String[] args) {
		MyExecutor myExecutor = new MyExecutor(1);
		
		
		for (int i = 100; i > 0; i--) {
			Runnable r = () ->{
				Util.sleep(1000);
//				System.out.println("Sleep 1000");
			};
			
			myExecutor.execute(r);
		}
		
		myExecutor.ex.shutdown();
		System.out.println("Overall executed: " + myExecutor.atomicInteger);
	}

}
