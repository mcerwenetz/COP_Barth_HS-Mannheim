package script_examples.chap8;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class ARFU {

	public static class Node<T> {
		private final T element;
		private volatile Node<T> next;

		public Node(T element) {
			this.element = element;
		}

		@Override
		public String toString() {
			return this.element.toString();
		}

	}

	private AtomicReferenceFieldUpdater<Node, Node> updater = AtomicReferenceFieldUpdater.newUpdater(Node.class,
			Node.class, "next");

	public static void main(String[] args) {
		ARFU main = new ARFU();
		Node<Integer> a = new Node<Integer>(1);
		Node<Integer> b = new Node<Integer>(2);
		main.updater.compareAndSet(a, null, b);
		System.out.println(a.next);
	}
}
