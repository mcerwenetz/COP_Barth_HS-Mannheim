package script_examples.chap6;

public class CallableExampleThread extends Thread {
	
	private CallableExample callableExample;

	public CallableExampleThread() {
		this.callableExample = new CallableExample();
	}
	
	@Override
	public void run() {
		try {
			Integer call = callableExample.call();
			System.out.println(call);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
