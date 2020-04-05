package a2.t1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import util.*;


public class CharacterRace {
	
	final static String alphabet = "abcdefghijklmnopqrstuvxyz";
//	final static String alphabet = "a";
	static Queue<Character> charlist = new LinkedList<Character>();

	public static void main(String[] args) {
		
		CharProdThread[] cpt_arr = new CharProdThread[alphabet.length()];
		AtomicBoolean keep_producing = new AtomicBoolean(true);
		Object lock = new Object();
		
		CharPeeker charPeeker = new CharPeeker(charlist, lock, keep_producing);
		
		for (int i = 0; i < alphabet.length(); i++) {
			cpt_arr[i] = new CharProdThread(i, keep_producing, charlist, lock);
		}
		for (CharProdThread charProdThread : cpt_arr) {
			charProdThread.start();
		}
		charPeeker.start();
		for (CharProdThread charProdThread : cpt_arr) {
			try {
				charProdThread.join();
				charPeeker.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Winner " + charPeeker.winner);
	}

}
