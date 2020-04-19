package a2.t5;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import util.Util;

public class OddEvenRecycle {
	public static void main(String[] args) {
		BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
		AtomicBoolean keep_running = new AtomicBoolean(true);
		Producer producer = new Producer(queue, keep_running);
		
		CheckWhere checkeven = new CheckWhere() {

			@Override
			public boolean checkWhere(Integer a) {
				if (a%2 == 0) {
					return true;
				}else {
					return false;
				}
			}
		};
		
				
		CheckWhere checkodd = new CheckWhere() {
			
			@Override
			public boolean checkWhere(Integer a) {
				if (a%2 != 0) {
					return true;
				}else {
					return false;
				}
			}
		};
		
		Consumer evenconsumer = new Consumer(queue, keep_running, checkeven);
		Consumer oddconsumer = new Consumer(queue, keep_running, checkodd);
		

		producer.start();
		evenconsumer.start();
		oddconsumer.start();
		
		Util.join(producer);
		Util.join(evenconsumer);
		Util.join(oddconsumer);
		
		System.out.println("Even: " + evenconsumer.getSum());
		System.out.println("Odd: " + oddconsumer.getSum());

	}
	
}
