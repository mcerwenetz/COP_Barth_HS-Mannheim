package a0.t3;

import java.util.Arrays;

public class Mergesort {
	public static int[] mergesort(int[] unsorted) {
		int[] ret =unsorted.clone();
		Arrays.sort(ret);
		return ret;
	}
	
	public static boolean is_sorted(int[] arr_sorted, int[] arr_unsorted) {
		boolean ret=true;
		int[] localsorted = arr_unsorted.clone();
		Arrays.sort(localsorted);
		for(int i=0; i < arr_sorted.length; i++) {
			if(!(arr_sorted[i] == localsorted[i] )) {
				ret = false;
			}
		}
		return ret;
	}
}
