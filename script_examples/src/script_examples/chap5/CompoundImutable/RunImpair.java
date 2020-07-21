package script_examples.chap5.CompoundImutable;

import script_examples.Util;

public class RunImpair {
	
	ImPair ip = new ImPair(0, 0);
	volatile Integer val = Integer.valueOf(0);
	volatile boolean run = true;
	
	class Incer extends Thread{
		@Override
		public void run() {
			while (run) {
				ip = new ImPair(val, val);
				val++;
			}
		}
	}
	
	class Peeker extends Thread {
		@Override
		public void run() {
			while (run) {
				if(ip.getsum() %2 == 1)
					System.out.println("BAD");
			}
		}
	}

	
	public static void main(String[] args) {
		RunImpair runImpair = new RunImpair();
		runImpair.new Incer().start();
		for (int i = 0; i < 100; i++) {
			runImpair.new Peeker().start();
		}
		Util.sleep(3000);
		runImpair.run=false;
	}
}
