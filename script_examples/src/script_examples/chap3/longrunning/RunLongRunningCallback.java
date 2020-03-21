package script_examples.chap3.longrunning;

import script_examples.Util;

public class RunLongRunningCallback {
	public static void main(String[] args) {
		final int ith=10000;
		final UsePrime whendone = new UsePrime() {
			
			@Override
			public void usePrime(int prime) {
				System.out.println(ith);
				System.out.println(prime);
			}
		};
		
		LongRunningCallback lrc = new LongRunningCallback(ith, whendone);
		lrc.start();
		Util.join(lrc);
	}
}
