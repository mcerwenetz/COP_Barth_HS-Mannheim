package generics;

import java.awt.List;
import java.util.ArrayList;

public class Typsicher {
	
	public static void main(String[] args) {
//		Gibt nen Error
//		Object o = 42;
		
//		Gibt keinen Error, weil's als String initalisiert wurde
		Object o = "String";
		String s = (String) o;
		
		
//		Typsichere Erstellung einer generischen ArrayList
		ArrayList<String> meineListe = new ArrayList<String>();
		
		String str="Hallo";
//		Eigene Generic Class
		GenericClass<String> a = new GenericClass<String>();
		a.set_t(str);
		System.out.println(a.get_t());
		
//		Achtung hier gehen nur Strings
		a.gen_method(str);
		
//		Das geht aber. Alles in der Klasse muss nur den selben T-Typ haben
		GenericClass<Integer> b = new GenericClass<Integer>();
		b.gen_method(42);
		
	}
}
