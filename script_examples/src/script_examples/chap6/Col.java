package script_examples.chap6;

import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import script_examples.Util;

public class Col {
	public static void main(String[] args) {
		List<Integer> list = Collections.synchronizedList(new LinkedList<Integer>());
		final AtomicInteger sum = new AtomicInteger();

		Runnable drain = () -> {
			try {
				synchronized (list) {
					while (!list.isEmpty()) {
						list.remove(0);
					}
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Ouch\n");
			}
		};

		Runnable summing = () -> {
			List<Integer> locaList;
			synchronized (list) {
				locaList = new LinkedList<Integer>(list);
			}
			for (Integer integer : locaList) {
				sum.addAndGet(integer);
			}
		};

		for (int i = 0; i < 1000000; i++) {
			list.add(Integer.valueOf(17));
		}
		Thread[] ts = new Thread[10];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = new Thread(summing);
			ts[i].start();
		}
		for (int i = 0; i < 1000000; i++) {
			list.add(Integer.valueOf(17));
		}
		System.out.println(sum);
		Util.joinall(ts);
	}
}
