package java_refresh.nested;

public class Outer {
	static int c;
	int x;
	
	public class Inner {
		//ix is from instance
		int ix = Outer.this.x;
		//ic is static and from class. No need for "this"
		int ic = Outer.c;
		public int getOuterX() {
			return Outer.this.x;
		}
	}
	
	public static class StaticNested{
		int ic = Outer.c;
	}
	
	public Inner gI() {
		//Only returns an inner class. But not static so Outer is still needed.
		return new Inner();
	}
	
	public static void main(String[] args) {
		//Static Inner
		StaticNested nested=new StaticNested();
		//new Inner
		Inner inner;
		if(true) {
			//new Outer
			Outer outer = new Outer();
			//Extra inner without Outer
			inner = outer.gI();
		}
		//Outer is not used anymore but still needed so Inner can exist.
		//Every Nested Class needs an Instance of the Class it is nested in
		
		int x =17;
		//Finally inner is delete so outer can RIP
		inner = null;
	}
}
