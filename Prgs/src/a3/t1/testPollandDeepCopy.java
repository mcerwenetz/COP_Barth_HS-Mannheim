package a3.t1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class testPollandDeepCopy {

	@Test
	void test() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			list.add(i);
		}
		SynchronizedQueue<Integer> queue = MyCollections.synchronizedQueue(list);
		
		assertEquals(list.size(), queue.size());
		
		for (int i = 0; i < queue.size(); i++) {
			assertEquals(list.poll(), queue.poll());
		}
	}

}
