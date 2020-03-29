package a1.t2;

public class IntUnsafeState implements IntState {
	private int val;

	@Override
	public void inc(int amount) {
		val+=amount;

	}

	@Override
	public void dec(int amount) {
		val-=amount;

	}

	@Override
	public int getValue() {
		return val;
	}

	@Override
	public void setValue(int value) {
		val=value;

	}

}
