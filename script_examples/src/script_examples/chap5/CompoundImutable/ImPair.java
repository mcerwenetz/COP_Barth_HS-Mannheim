package script_examples.chap5.CompoundImutable;

public class ImPair {
	private final Integer i1;
	private final Integer i2;
	
	public ImPair(Integer i1, Integer i2) {
		super();
		this.i1 = i1;
		this.i2 = i2;
	}
	
	Integer getsum() {
		return i1+i2;
	}
	
	

}
