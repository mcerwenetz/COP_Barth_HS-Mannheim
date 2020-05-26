package script_examples.chap8;

import java.util.concurrent.atomic.AtomicReference;

public class NonblockingStack<T> {
	private static class Node<T> {
		// Element ist final
		// wir wollen den Stack benutzen,
		public final T element;
		// NextNode, wie bei linked List
		public Node<T> nextNode;

		// Konstruktor für die Node
		public Node(T element) {
			this.element = element;
		}
	}

	private AtomicReference<Node<T>> head;

	public NonblockingStack() {
		head = new AtomicReference<>();
	}

	public void push(T element) {
		Node<T> oldHead, node = new Node<>(element);
		boolean ok = false;
		/*
		 * Achtung hier immer neu getten falls es nicht geklappt hat. Sonst kann es
		 * passieren, dass 2 paralelle Threads 2 neue Nodes erstellen die beide auf
		 * oldhead zeigen. Wenn beide vorne einfügen ist der Stack nicht mehr liniear,
		 * sondern es gibt dann 2 heads. Und dass will man vermeiden.
		 */
		do {
			oldHead = head.get();
			node.nextNode = oldHead;
			ok = head.compareAndSet(oldHead, node);
		} while (!ok);
	}

	public T pop() {
		Node<T> newHead, oldHead;

		boolean ok = false;
		do {
			//hier das gleiche wie bei push. Immer neu getten falls er sich in zwischenzeit geändert hat.
			oldHead = head.get();
			//wenn der Stack leer ist soll natürlich auch kein Element zurückgeliefert werden.
			if (oldHead == null) {
				return null;
			}
			newHead = oldHead.nextNode;
			ok = head.compareAndSet(oldHead, newHead);
		} while (!ok);
		return oldHead.element;
	}
}
