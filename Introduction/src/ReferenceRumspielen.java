import java.math.BigInteger;
import java.util.LinkedList;


public class ReferenceRumspielen {


	
	public static void main(String[] args) {
		BigInteger aBigInteger = new BigInteger("0");
		Integer aInteger = new Integer(0);
		LinkedList<Integer> a = new LinkedList<Integer>();
		a.add(0);

		foo(aBigInteger);
		foo2(aInteger);
		foo3(a);
		System.out.println(aBigInteger.toString());
		System.out.println(aBigInteger.toString());
		System.out.println(a.toString());
	}

	private static void foo3(LinkedList<Integer> a) {
		a.add(1);
		
	}

	private static void foo2(Integer aInteger) {
		aInteger++;
		
	}

	private static void foo(BigInteger aBigInteger) {
		aBigInteger=aBigInteger.add(BigInteger.ONE);
	}
	
}
