package script_examples.chap3.longrunning;

import script_examples.Util;

public class RunLongRunning {
	public static void main(String[] args) {
		int ith=1000;
		LongRunning lr =new LongRunning(ith);
		lr.start();
		//without callback
		Util.join(lr);
		int prime = lr.getPrime();
		System.out.println(prime);
	}
	
}
