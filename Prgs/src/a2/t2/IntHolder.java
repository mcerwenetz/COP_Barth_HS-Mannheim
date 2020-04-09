package a2.t2;

public class IntHolder {
	Integer i;
	
	public IntHolder(Integer i) {
		this.i=i;
	}

	public Integer getI() {
		return i;
	}

	public void setI(Integer i) {
		this.i = i;
	}
	
	public void plusplus() {
		i++;
	}
	
	@Override
	public String toString() {
		return i.toString();
	}

}
