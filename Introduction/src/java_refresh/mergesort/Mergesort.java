package java_refresh.mergesort;

public class Mergesort {
	
	public static int[] array = {1,3,2};
	
	public int[] sort(int links, int rechts) {
		if(links < rechts) {
			int q = (links +rechts)/2;
			
			sort(links, q);
			sort(q+1, rechts);
			merge(links, q ,rechts);
		}
		return array;
	}
	
	private void merge(int links, int q, int rechts) {
		int[] arr = new int[array.length];
		int i,j;
		for(i=links; i <=q; i++) {
			arr[i] = array[i];
		}
		for(j=q+1; j <=rechts; j++) {
			arr[rechts + q +1 -j] = array[j];
		}
		i=links;
		j=rechts;
		for(int k = links; k<=rechts; k++) {
			if(arr[i] <= arr[j]) {
				array[k]=arr[k];
				i++;
			}else {
				array[k]=arr[j];
				j--;
			}
		}
	}
	
	public static void main(String[] args) {
		Mergesort ms = new Mergesort();
		int[] arr = ms.sort(0, array.length-1);
	}

}
