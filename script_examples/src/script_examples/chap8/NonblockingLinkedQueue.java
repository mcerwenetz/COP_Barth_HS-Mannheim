package script_examples.chap8;

import java.util.concurrent.atomic.AtomicReference;

public class NonblockingLinkedQueue<T> {

	private static class Node<T> {
		// Element ist final
		// wir wollen den Stack benutzen,
		public final T element;
		// NextNode, wie bei linked List
		public AtomicReference<Node<T>> next;

		// Konstruktor für die Node
		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = new AtomicReference<>(next);
		}
	}

	private final Node<T> dummyNode = new Node<T>(null, null);
	private AtomicReference<Node<T>> head, tail;

	public NonblockingLinkedQueue() {
		head = new AtomicReference<>();
		tail = new AtomicReference<>();

		head.set(dummyNode);
		tail.set(dummyNode);
	}

	public boolean put(T element) {
		// erstelle neue Node
		Node<T> newNode = new Node<T>(element, null);
		while (true) {
			// Der aktuelle tail ist der tail.
			// Wir wollen Tail verschieben also brauchen wir eine Speichereferenz
			Node<T> currentTail = tail.get();
			// Der nächste von aktueller Tail ist der nächste von tail
			Node<T> tailNext = currentTail.next.get();
			// Wenn sich der tail schon geändert hat versuch's erst gar nicht
			if (currentTail == tail.get()) {
				/*
				 * wenn sich der tail nicht geändert hat aber die aktuell nächste Node auch
				 * nicht null ist, dann wurde die neue Node schon eingefügt aber der tail-Zeiger
				 * nicht verschoben.
				 */
				if (tailNext != null) {
					// Tail auf den letzten wirklich letzten Eintrag setzen.
					tail.compareAndSet(currentTail, tailNext);
				}
				/*
				 * wenn sich tail nicht geändert hat und die aktuell nächste Node null ist,
				 * wurde weder die neue Node an die Liste angehängt, noch der tail-Zeiger der
				 * Liste auf die neue Node gesetzt.
				 */
				else {
					// Nochmal sichergehen dass es wirklich kein neues ELement gibt und dann neue
					// Node einsetzen
					if (currentTail.next.compareAndSet(null, newNode)) {
						// wenigstens versuchen tail noch zu setzen. Auch wenn es zum Kontextwechsel
						// kommen kann.
						tail.compareAndSet(currentTail, newNode);
						return true;
					}
				}
			}
		}

	}

	public T take() {
		while (true) {
			Node<T> currentHead = head.get();
			// Wir brauchen auch tail. Falls die Liste leer ist.
			Node<T> currentTail = tail.get();
			// und das erste Elemente weil wir vorne rausnehmen
			Node<T> first = currentHead.next.get();

			// wenn head nicht currenthead ist fang erst gar nicht an
			if (head.get() == currentHead) {
				/*
				 * wenn currenthead auch current tail ist, dann könnten 2 Sachen sein: 1. Die
				 * Liste ist wirklich leer. Dann müsste headNext auch null sein. 2. Die Liste
				 * ist nicht leer aber tail zeigt warum auch immer nicht drauf. Dann wäre
				 * headNext nicht leer.
				 */
				if (currentHead == currentTail) {
					// is headnext null ist die liste leer
					if (first == null) {
						return null;
						// ist headnext nicht null dann zeigt Tail einfach nicht drauf.
					} else {
						// tail manuell auf den ersten Eintrag setzen
						tail.compareAndSet(currentTail, first);
					}
					// falls currentHead nicht currentTail ist dann ist die Liste garantiert nicht
					// leer
				} else {
					// Das ist das Element das returned wird
					T ret = first.element;
					// Falls der head erfolgreich gesetzt wurde kann man's auch returnen
					if (head.compareAndSet(currentHead, first)) {
						return ret;
					}
				}
			}
		}

	}

	public static void main(String[] args) {
		NonblockingLinkedQueue<Integer> queue = new NonblockingLinkedQueue<>();

		queue.put(1);
		queue.put(2);
		
		Integer integer =queue.take();
	}
}
