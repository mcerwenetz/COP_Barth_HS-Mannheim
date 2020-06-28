package a7.t2;

//Antwortet Child Master (Checkprimes to Countprimes)
public class MsgIsPrimeResult {

	final private long value;
	final private boolean prime;
	
	public MsgIsPrimeResult(long value, boolean prime) {
		this.value = value;
		this.prime = prime;
	}
	
	public long getValue() {
		return value;
	}
	
	public boolean isPrime() {
		return prime;
	}
}
