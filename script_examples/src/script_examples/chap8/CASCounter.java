package script_examples.chap8;

public class CASCounter {
	private SimulatedCAS value = new SimulatedCAS();

	public int getValue() {
		return value.get();
	}

	public int incrementAndGet() {
		int alterWert = value.get();
		boolean hatgeklappt = false;
		while (!hatgeklappt) {
			hatgeklappt = value.compareAndSet(alterWert, alterWert + 1);
		}
		return alterWert+1;
	};
	
	public static void main(String[] args) {
		CASCounter casCounter = new CASCounter();
		System.out.println(casCounter.getValue());
		casCounter.incrementAndGet();
		System.out.println(casCounter.getValue());
	}

}
