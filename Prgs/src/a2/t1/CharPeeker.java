package a2.t1;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;


public class CharPeeker extends Thread {
	private Queue<Character> charlist;
	static Object lock = new Object();
	AtomicBoolean keep_producing;
	Map<Character, Integer> counters = new HashMap<Character, Integer>();
	volatile boolean keep_running = true;
	
	Character winner;
	
	public CharPeeker(Queue<Character> charlist, Object lock, AtomicBoolean keep_producing) {
		for (Character character : CharacterRace.alphabet.toCharArray()) {
			counters.put(character, 0);
		}
		this.charlist=charlist;
		CharPeeker.lock=lock;
		this.keep_producing=keep_producing;
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				c = charlist.remove();
			}
			countchar(c);
			if(limit_reached()) {
				keep_producing.set(false);
				keep_running=false;
			}
		}
	}
	private boolean limit_reached() {
		boolean a=false;
		for (Character character : CharacterRace.alphabet.toCharArray()) {
			if(counters.get(character) < 1000) {
				a= false;
			}else {
				winner=character;
				a= true;
				break;
			}
		}
		return a;
	}

	private void countchar(Character c) {
		counters.replace(c, counters.get(c)+1);
	}

}
