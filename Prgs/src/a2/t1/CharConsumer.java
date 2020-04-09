package a2.t1;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;


public class CharConsumer extends Thread {

	private Queue<Character> charlist;
	private Object lock = new Object(); // pb: kein static, static ist fast immer böse
	private AtomicBoolean keep_producing;
	private Map<Character, Integer> counters = new HashMap<Character, Integer>();
	boolean keep_running = true; // pb: muss nicht volatil sein, nur in dem Thread genutzt

	private char winner = 0; // pb: okay, nur kein Lock gebraucht, wenn es nach dem join gelesen wird
	private Integer winner_threshold=100;
	
	public CharConsumer(Queue<Character> charlist, Object lock, AtomicBoolean keep_producing) {
		for (Character character : CharacterRace.alphabet.toCharArray()) {
			counters.put(character, 0);
		}
		this.charlist = charlist;
		this.lock = lock;
		this.keep_producing = keep_producing;
	}
	
	@Override
	public void run() {
		while(keep_running) {
			Character c = null;
			synchronized (lock) {
				while(charlist.isEmpty()) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
				c = charlist.remove();
			}
			countchar(c);
			// pb, sie wissen ja, dass es nur für c passiert sein kann
			// das sollte man noch fixen
			if (limit_reached()) {
				keep_producing.set(false);
				keep_running = false;
			}
		}
	}
	
	private boolean limit_reached() {
		// pb, man muss ja schon nicht durch alle Zeichen gehen,
		// aber wozu die extra Variable?
		for (Character character : CharacterRace.alphabet.toCharArray()) {
			if (counters.get(character) >= winner_threshold) {
				winner = character;
				return true;
			}
		}
		return false;
	}

	private void countchar(Character c) {
		counters.put(c, counters.get(c)+1); // pb: put ist okay
	}

	// pb: Darf nur nach join gelesen werden wegen Sichtbarkeit
	public char getWinner() {
		return winner; 
	}

}
