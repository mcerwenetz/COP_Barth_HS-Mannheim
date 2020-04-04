package script_examples.chap3.hazards;

public class DeadLockEmbrace {

	private Object l1, l2;
	
	public DeadLockEmbrace() {
		l1=new Object();
		l2=new Object();
	}
	
	public void doIt12() {
		synchronized (l1) {
			synchronized (l2) {
				
			}
		}
	}
	
	public void doIt21() {
		synchronized (l2) {
			synchronized (l1) {
				
			}
		}
		
	}
	
}
