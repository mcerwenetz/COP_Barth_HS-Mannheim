package script_examples.chap3.control_of_execution;

public class IncerInterrupt extends Thread {
	private volatile boolean doRun = true;
	
	@Override
	public void run() {
		int x = 0;
		
		while(doRun) {
			x++;
			System.out.println(x);
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				
			}
		}
	}
	
	public void quit() {
		doRun = false;
		this.interrupt();
	}
}
