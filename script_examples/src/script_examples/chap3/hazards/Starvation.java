package script_examples.chap3.hazards;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import script_examples.Util;

public class Starvation extends Thread {
	final static int NO_OF_THREADS =16;
	final static int SPECIAL=NO_OF_THREADS-1;
	static Lock[] locks = new Lock[NO_OF_THREADS];
	static int[] values = new int [NO_OF_THREADS];
	
	static {
		for (int i = 0; i < locks.length; i++) {
			locks[i]= new ReentrantLock();
		}
	}
	
	private int id;
	
	public Starvation(int id) {
		this.id = id;
	}
	
	@Override
	public void run() {
		while(!isInterrupted()) {
			if(id==SPECIAL) {
				doSpecial();
			}else {
				doNormal();
			}
		}
	}

	private void doNormal() {
		if(locks[id].tryLock()) {
			values[id]++;
			locks[id].unlock();
		}
		
	}

	private void doSpecial() {
		boolean[] havIt = new boolean[NO_OF_THREADS];
		boolean hasAll = true;
		for (int i = 0; i < locks.length; i++) {
			havIt[i] = locks[i].tryLock();
			if(!havIt[i]) {
				hasAll =false;
			}
		}
		
		if (hasAll) {
			values[id]++;
		}
		
		for (int i = 0; i < locks.length; i++) {
			if(havIt[i]) {
				locks[i].unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		Starvation[] t_arr = new Starvation[NO_OF_THREADS];
		for (int i = 0; i < NO_OF_THREADS; i++) {
			t_arr[i] = new Starvation(i); 
		}
		for(Thread t : t_arr) {
			t.start();
		}
		Util.sleep(3000);
		for(Thread t : t_arr) {
			t.interrupt();
		}
		for (int i = 0; i < t_arr.length; i++) {
			System.out.println(i + " " + values[i]);
//			if ((i+1)%2 == 0) {
//				System.out.println("\n");
//			}
		}
	}
}
