package a2.t2;

import java.math.BigInteger;

public class BigIntHolder {
	BigInteger b;
	
	public BigInteger getB() {
		return b;
	}

	public void setB(BigInteger b) {
		this.b = b;
	}

	public BigIntHolder(BigInteger b) {
		this.b=b;
	}

	public void plusplus() {
		b=b.add(BigInteger.ONE);
	}
	
	@Override
	public String toString() {
		return b.toString();
	}

}
