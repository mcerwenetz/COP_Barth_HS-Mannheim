package anon_classes;

public class AnonClass {
	public void doIt(final int j) {
		final int i=17;
		//Anonymous runnable is created and instantiated
		Runnable runnable = new Runnable() {
			@Override
			public void run( ) {
				System.out.println(i);
				System.out.println(j);
			}
		};
	}
}
