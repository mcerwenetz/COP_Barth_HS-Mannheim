package a3.t1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class SynchronizedQueue<T> implements Iterable<T> {
	private Queue<T> queue;

	public SynchronizedQueue(Queue<T> queue) {
		this.queue = new LinkedList<>(queue);
	}

	public synchronized boolean offer(T t) {
		return queue.offer(t);
	}

	public T poll() {
		synchronized (this) {
			return queue.poll();
		}
	}

	public int size() {
		return queue.size();
	}

	@Override
	public Iterator<T> iterator() {
		return queue.iterator();
	}
	
}
