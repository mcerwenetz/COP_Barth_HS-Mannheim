package a2.t1;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import util.*;

public class CharProdThread extends Thread {
	
	private Queue<Character> charlist;
	Object lock;
	AtomicBoolean keep_producing;
	
	private int id;
	
	CharProdThread(int id, AtomicBoolean keep_producing, Queue<Character> characters, Object lock){
		this.id=id;
		this.keep_producing=keep_producing;
		this.charlist=characters;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		while(keep_producing.get()) {
			synchronized (lock) {
				charlist.add(CharacterRace.alphabet.charAt(id));
				lock.notify();
			}
//			Util.sleep(10000);
		}
	}
}
