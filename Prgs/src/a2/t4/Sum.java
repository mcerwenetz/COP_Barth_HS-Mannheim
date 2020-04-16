package a2.t4;

public class Sum {
	Integer sum;

	public Integer getSum() {
		return sum;
	}

	public void setSum(Integer sum) {
		this.sum = sum;
	}

	public Sum(Integer sum) {
		super();
		this.sum = sum;
	}
	
	public void increaseBy(Integer by) {
		sum+=by;
	}

	@Override
	public String toString() {
		return sum.toString();
	}
}
