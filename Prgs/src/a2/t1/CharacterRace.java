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
		CharConsumer consumer = new CharConsumer(charlist, lock, keep_producing);		
		consumer.start(); // pb: zusammen lassen, oder nach oben
		
		// pb: sorry, das war der eigentlich schon; also zuerst den joinen ist ein
		// funktionaler Unterschied. Der setzt ja die Variable, die den anderen
		// klar macht, dass es soweit ist auf wahr. Dann machen wir den fertig
		// und warten auf die anderen. 
		// Aber ganz ehrlich, bei  mir hat das von vornherein immer geklappt und
		// eigentlich müsste es auch bei Ihnen klappen. Da sie ja nur jedesMal gejoint
		// haben und das nach dem ersten Mal ja einfach nur immer klappt.
		// Dann bleibt nur die xxGB RAM, die Ihre erste Lösung vielleicht erzeugt hat.
		// Das ist jetzt nicht mehr so
		try {
			consumer.join(); // pb: zuerst den joinen, nicht jedesmal joinen
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
		System.out.println("Winner " + consumer.getWinner()); // pb: immmer getter
	}

}
