package a1.t2;

public class IntSafeState implements IntState {
	private int value;

	@Override
	public void inc(int amount) {
		synchronized (this) {
			value+=amount;
		}
	}

	@Override
	public void dec(int amount) {
		synchronized (this) {
			value-=amount;
		}

	}

	@Override
	public int getValue() {
		return this.value;
	}

	@Override
	public void setValue(int value) {
		this.value=value;
	}

}
