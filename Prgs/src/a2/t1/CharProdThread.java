package a2.t1;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class CharProdThread extends Thread {
		
	private Queue<Character> charlist;
	private Object lock;
	private AtomicBoolean keep_producing;
	
	private int id;
	
	// pb, ich würde nur das zu produzierende Zeichen übergeben 
	// und keine ganze Liste
	CharProdThread(int id, AtomicBoolean keep_producing, Queue<Character> characters, Object lock){
		this.id = id;
		this.keep_producing = keep_producing;
		this.charlist = characters;
		this.lock = lock;
	}
	
	@Override
	public void run() {
		Random random = new Random();
		while(keep_producing.get()) {
			synchronized (lock) {
				charlist.add(CharacterRace.alphabet.charAt(id));
				lock.notify();
			}
			// pb: schlafen ist wichtig, bei mir waren mal so zwischen
			// 2 und 4 Gig RAM zu mit Zeichen, das kann sehr stressig sein
			try {
				Thread.sleep(random.nextInt(21));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
