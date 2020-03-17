package lambda;

public class Lambda {
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
		runnable.run();
	}
	
	public void doItLambda(final int j) {
		final int i =17;
		Runnable runnable = () -> {
			System.out.println(i);
			System.out.println(j);
		};
		runnable.run();
	}
}
