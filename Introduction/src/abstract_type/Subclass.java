package abstract_type;

public class Subclass extends Abstract {

	//abstrakte Elternmethoden müssen überschrieben werden.
	//Normale Methoden müssen's nicht.
	@Override
	void abstrastractmethod(int b) {
		// TODO Auto-generated method stub

	}
	public static void main() {
		//Abstrakte Klassen können nicht instanziert werden
//		Abstract a = new Abstract();
		Subclass b = new Subclass();
	}
	
//	Abstrakte Methoden sind nur in Abstrakten Klassen möglich
//	abstract int abs_sub_method();

}
