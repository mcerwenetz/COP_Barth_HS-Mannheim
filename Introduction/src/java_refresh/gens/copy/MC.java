package java_refresh.gens.copy;

import interfaces.MyCallable;

//public class MC implements MyCallableObject {
//	//Error prone. Can return any object.
//	public Object call() {return "Hello";}
//	
//	public static void main(String[] args) {
//		MC mc = new MC();
//		String msg = (String) mc.call();
//	}
//	
//}

public class MC implements MyCallable<String>{
	//Only String is allowed. Compiler error not RuntimeError
	public String call() {return "Hello";}
	
	public static void main(String[] args) {
	MC mc = new MC();
	String msg = (String) mc.call();
	}
}
