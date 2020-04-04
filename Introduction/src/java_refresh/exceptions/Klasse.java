package java_refresh.exceptions;

public class Klasse {
	public void g() throws MyException {
		throw new MyException();
	}

	public static void main(String[] args) {
		try {
			//throws new MyException
			new Klasse().g();
			// Exception is caught
		} catch (MyException e) {
			//new Runtime Exception is thrown
			throw new RuntimeException(e);
			//Runtime Exception caught
		} catch(RuntimeException b) {
		} finally {
			//eventually executed
			System.out.println("Will always print");
		}
	}
}