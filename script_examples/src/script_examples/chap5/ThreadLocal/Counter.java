package script_examples.chap5.ThreadLocal;

import java.util.concurrent.atomic.AtomicLong;

import script_examples.Util;

public class Counter {
	private int count;
	public Counter() {
		count=0;
	}
	public void inc() {
		count++;
	}
	
	public int val() {
		return count;
	}
	
	static ThreadLocal<Counter> tcnt = new ThreadLocal<Counter>() {
		public Counter initialValue() {
			return new Counter();
		}
	};
	
	static AtomicLong allc = new AtomicLong();
	static int grounds = 100_000;
	
	
	public static void main(String[] args) {
		Thread[] t = new Thread[10];
		for (int i = 0; i < t.length; i++) {
			t[i] = new Thread() {
				public void run() {
					int rounds = grounds;
					while (rounds-- > 0) {
						tcnt.get().inc();
					}
					allc.addAndGet(tcnt.get().val());
				}
			};
		}
		
		for (Thread thread : t) {
			thread.start();
		}
		for (Thread thread : t) {
			Util.join(thread);
		}
		if(allc.longValue() != t.length*grounds) {
			System.out.println("Error" + allc.longValue());
		}
		System.out.println("fine");
	}
}
