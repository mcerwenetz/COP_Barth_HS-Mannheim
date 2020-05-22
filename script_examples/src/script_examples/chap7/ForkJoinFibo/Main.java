package script_examples.chap7.ForkJoinFibo;

import java.util.concurrent.ForkJoinPool;

public class Main {
	public static void main(String[] args) {
		Integer n = 10;
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Fibo fibo = new Fibo(n);
		Integer resultInteger = forkJoinPool.invoke(fibo);
		System.out.println(resultInteger);
	}
}
