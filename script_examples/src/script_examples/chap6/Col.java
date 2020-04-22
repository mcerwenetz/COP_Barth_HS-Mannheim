package script_examples.chap6;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import script_examples.Util;

public class Col {
	public static void main(String[] args) {
		List<Integer> list = Collections.synchronizedList(new LinkedList<Integer>());
		Runnable drain = () -> {
			try {
				while(list.isEmpty()) {
					list.remove(0);
				}
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Ouch\n");
			}
		};
		for (int i = 0; i < 1000000; i++) {
			list.add(Integer.valueOf(17));
		}
		Thread[] ts = new Thread[10];
		for (int i = 0; i < ts.length; i++) {
			ts[i]= new Thread(drain);
			ts[i].start();
		}
		Util.joinall(ts);
	}
}
