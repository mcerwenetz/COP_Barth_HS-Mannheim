package script_examples.chap3.counter;

import script_examples.Util;

public class RunMeasureCounter {
	public static void main(String[] args) {
		RunCounter runCounter = new RunCounter();
		Counter uCounter = new UnsafeCounter();
		Counter sCounter = new SafeCounter();
		Util.resetTime();
		runCounter.run(uCounter);
		long umillis = Util.getTimeMilis();
		Util.resetTime();
		runCounter.run(sCounter);
		long smillis = Util.getTimeMilis();
		long diff = smillis - umillis;
		long ratio = smillis/umillis;
		System.out.println("safe " + smillis + " unsafe "+umillis);
		System.out.println("Thats a difference of " + diff + " ms"+ ". Unsafe is " + ratio + " times faster");
	}
}
