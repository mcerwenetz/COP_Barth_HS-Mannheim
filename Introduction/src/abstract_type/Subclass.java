package abstract_type;

public class Subclass extends Abstract {

	//abstrakte Elternmethoden m�ssen �berschrieben werden.
	//Normale Methoden m�ssen's nicht.
	@Override
	void abstrastractmethod(int b) {
		// TODO Auto-generated method stub

	}
	public static void main() {
		//Abstrakte Klassen k�nnen nicht instanziert werden
//		Abstract a = new Abstract();
		Subclass b = new Subclass();
	}
	
//	Abstrakte Methoden sind nur in Abstrakten Klassen m�glich
//	abstract int abs_sub_method();

}
