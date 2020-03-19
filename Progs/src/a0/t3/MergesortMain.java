package a0.t3;

import java.util.Random;

public class MergesortMain {
	
	
	
		static int[] create_random_array(int size) {
		Random rand = new Random();
		int[] array = new int[size];
		for(int i=0; i < size; i++) {
			array[i]=rand.nextInt();
		}
		return array;
		}
	

	public static void main(String[] args) {
		int arr[] = {3,4,8,7,49,14};
		int[] arr_sorted=Mergesort.mergesort(arr);
		System.out.println("Array is sorted: "+Mergesort.is_sorted(arr_sorted, arr));
		
		
		int arr2[] = create_random_array(Integer.parseInt(args[0]));
		long start_time = System.nanoTime();
		arr2 = Mergesort.mergesort(arr2);
		long stop_time = System.nanoTime();
		System.out.println("This took " + (stop_time-start_time)/10e9 + "s");
		
	}

}
