package script_examples.chap7;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class DependentTasks {
	
//	static ExecutorService es = Executors.newSingleThreadExecutor();
	static ExecutorService es = Executors.newCachedThreadPool();
	static FutureTask<Integer> genFac(final int i){
		Callable<Integer> ret = () -> {
			if(i==1)
				return i;
			FutureTask<Integer> ft = genFac(i-1);
			es.submit(ft);
			try {
				return i*ft.get();
			} catch (Exception e) {
				throw new RuntimeException();
			}
		};
		return new FutureTask<Integer>(ret);
	}
	
	public static void main(String[] args) {
		FutureTask<Integer> ft = genFac(12);
		es.submit(ft);
		try {
			System.out.println(ft.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		es.shutdownNow();
	}

}
