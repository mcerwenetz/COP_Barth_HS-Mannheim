package a3.t1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ConcurrentModificationException;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import util.Util;


class synchronizedTest {

	@Test
	void test() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		SynchronizedQueue<Integer> queue = MyCollections.synchronizedQueue(list);
		final ExpectedException exception = ExpectedException.none();
		
		
		Runnable r = () -> {
			for (int i = 0; i < 10; i++) {
				queue.offer(i);
			}
		};
		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
		exception.expect(ConcurrentModificationException.class);
		Util.join(t1);
		Util.join(t2);
		
		
		
	}

}
