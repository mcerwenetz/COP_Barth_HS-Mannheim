package script_example.chap3.primegen;

import java.util.ArrayList;
import java.util.List;

public class PrimeGen {
	//return a List of divisors for a given number
	private List<Integer> getDivisible(int x){
		List<Integer> divs = new ArrayList<>();
		for(int div=2; div < x; div+=1) {
			if(x % div == 0) {
				divs.add(div);
			}
		}
		return divs;
	}
	
	//internal primvalue which is increased by next 
	//to get the ith Prime
	private int prime =1;
	
	//return the prime for the current number called
	public int next() {
		do {
			prime+=1;
			//if there is no divisor current value of prime must be a prime
		}while(!getDivisible(prime).isEmpty());
		return prime;
	}
	
	public static void main(String[] args) {
		PrimeGen p = new PrimeGen();
		System.out.println(p.next());
	}

}
