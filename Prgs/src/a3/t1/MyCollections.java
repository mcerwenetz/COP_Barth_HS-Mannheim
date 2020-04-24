package a3.t1;

import java.util.Queue;

public class MyCollections {
	
	public static <T> SynchronizedQueue<T> synchronizedQueue(Queue<T> queue){
		return new SynchronizedQueue<T>(queue);
	}
	
	public MyCollections() {
	}
	
}