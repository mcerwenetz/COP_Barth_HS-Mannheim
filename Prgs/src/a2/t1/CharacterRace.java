package a2.t1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;


public class CharacterRace {
	
	// pb: w was missing
	final static String alphabet = "abcdefghijklmnopqrstuvwxyz";
//	final static String alphabet = "a";
	static Queue<Character> charlist = new LinkedList<Character>();

	public static void main(String[] args) {		
		CharProdThread[] cpt_arr = new CharProdThread[alphabet.length()];
		AtomicBoolean keep_producing = new AtomicBoolean(true);
		Object lock = new Object();
		
		// pb: immer die Länge des Feldes nehmen auf das man Zugriff (Doku)
		for (int i = 0; i < cpt_arr.length; ++i) {
			cpt_arr[i] = new CharProdThread(i, keep_producing, charlist, lock);
		}
		for (CharProdThread charProdThread : cpt_arr) {
			charProdThread.start();
		}
		CharPeeker charPeeker = new CharPeeker(charlist, lock, keep_producing);		
		charPeeker.start(); // zusammen lassen, oder nach oben
		
		try {
			charPeeker.join(); // zuerst den joinen, nicht jedesmal joinen
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		for (CharProdThread charProdThread : cpt_arr) {
			try {
				charProdThread.join();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		System.out.println("Winner " + charPeeker.getWinner()); // pb: immmer getter
	}

}
