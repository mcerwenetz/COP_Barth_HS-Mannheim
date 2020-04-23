package script_examples.chap6;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOWArrayList {
	public static void main(String[] args) {
		CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>(new Integer[] { 1, 2, 3 });

		Iterator<Integer> iterator1 = list.iterator();
		list.add(4);
		Iterator<Integer> iterator2 = list.iterator();

		System.out.println(list);
		iterator1.forEachRemaining(System.out::println);
		System.out.println("----------------");
		iterator2.forEachRemaining(System.out::println);
	}
}
