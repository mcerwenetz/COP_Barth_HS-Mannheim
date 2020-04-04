package script_examples.chap3;

import script_examples.Util;

public class Unsychronised {
	
	static int i=0, j=0,k=0;
	
	public static class T extends Thread{
		@Override
		public void run() {
			while (i==0) {
				k++;
				System.out.println("done " + j);
			}
		}
	};
	
	public static void main(String[] args) {
		new T().start();
		j=1;
		//Don't kill immediately
		Util.sleep(10);
		i=1;
	}

}
