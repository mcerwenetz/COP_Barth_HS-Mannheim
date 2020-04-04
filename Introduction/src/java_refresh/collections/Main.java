package java_refresh.collections;

import java.util.ArrayList;
import java.util.Collections;



public class Main {

	public static void main(String[] args) {
		ArrayList<String> a = new ArrayList<String>();
		a.add("Maus");
		a.add("Katze");
		a.add("Hund");
		
		Collections.sort(a);
		System.out.println(a);
		
		Collections.swap(a, 1, 2);
		System.out.println(a);
		
		Collections.sort(a);
		int index = Collections.binarySearch(a, "Katze");
		System.out.println("Katze befindet sich an Index: " + index + " Nach binary search");
	}

}
