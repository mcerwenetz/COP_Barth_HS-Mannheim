package a7.t2;

//Fragt CountPrimes Checkprimes (Master to child)
public class MsgIsPrime {
	final private long value;
	public MsgIsPrime(long value) {
		//immutable
		this.value=value;
	}
	
	public long getValue() {
		return value;
	}
}
