import java.math.BigInteger;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ReferenceRumspielen {


	
	public static void main(String[] args) {
		BigInteger aBigInteger = new BigInteger("0");
		Integer aInteger = 0;
		LinkedList<Integer> a = new LinkedList<Integer>();
		a.add(0);
		IntegerHolder inthold = new IntegerHolder(0);
		ReentrantLock l = new ReentrantLock();

		foo(aBigInteger);
		foo2(aInteger);
		foo3(a);
		foo4(inthold);
		foo5(l);
		
		System.out.println(aBigInteger.toString());
		System.out.println(aBigInteger.toString());
		System.out.println(a.toString());
		System.out.println(inthold.getInteger().toString());
	}

	private static void foo5(ReentrantLock l) {
		l.lock();
		l.unlock();
	}

	private static void foo4(IntegerHolder inthold) {
		inthold.add(1);
	}

	private static void foo3(LinkedList<Integer> a) {
		a.add(1);
		
	}

	private static void foo2(Integer aInteger) {
//		Integer bInteger = new Integer(aInteger.intValue())
//		aInteger = bInteger;
		aInteger++;
		
	}

	private static void foo(BigInteger aBigInteger) {
		aBigInteger=aBigInteger.add(BigInteger.ONE);
	}
	
}
