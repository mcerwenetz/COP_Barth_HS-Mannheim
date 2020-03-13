package generics;

import com.sun.glass.ui.CommonDialogs.Type;

public class GenericClass<T> {
	private T t;
	
	public void set_t(T t)
	{
		this.t=t;
	}
	
	public T get_t() {
		return t;
	}
	
	public void gen_method(T a){
		System.out.print(a.getClass());
	}
}
