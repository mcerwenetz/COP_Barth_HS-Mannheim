package script_examples.chap6;

import java.util.Random;
import java.util.concurrent.Callable;

public class CallableExample implements Callable<Integer> {
	
	Random random = new Random();
	
	@Override
	public Integer call() throws Exception {
		int rand;
		Thread thread = Thread.currentThread();
		do {
			rand=random.nextInt(Integer.MAX_VALUE);
			if(thread.isInterrupted()) {
				throw new InterruptedException();
			}
		} while (rand%100000000 != 17171717);
		return rand;
	}
	
	public static void main(String[] args) {
		CallableExample callableExample = new CallableExample();
		Integer res=0;
		try {
			res = callableExample.call();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(res);
	}
}
