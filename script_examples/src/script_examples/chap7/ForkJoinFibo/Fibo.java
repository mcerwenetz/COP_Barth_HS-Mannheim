package script_examples.chap7.ForkJoinFibo;

import java.util.concurrent.RecursiveTask;

public class Fibo extends RecursiveTask<Integer>{
	
	final int n;
	public Fibo(Integer number) {
		this.n=number;
	}
	
	@Override
	protected Integer compute() {
		if (n <= 1) {
			return n;
		}
		
		Fibo f1 = new Fibo(n-1);
		f1.invoke();
		Fibo f2 = new Fibo(n-2);
		f2.invoke();
		return f2.join() + f1.join();
	}
	
}
