package wrapper;

public class Wrapper {

	public static void main(String[] args) {
		int v = 17;
		Integer wrapv = v;
		wrapv = Integer.valueOf(v);
		
		v++;
		wrapv++;
		if(v-wrapv == 0) {
			System.out.println("ok");
		}

	}

}
