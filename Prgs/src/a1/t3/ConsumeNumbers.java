package a1.t3;

import java.net.URL;
import java.net.MalformedURLException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

// Instances of ConsumeNumbers may retrieve several numbers 
// (one each by calling next) from the WebService provided
// by ServeNumbers. Do not change this file. Look at main 
// for a simple usage of ConsumeNumbers.

public class ConsumeNumbers {
	private final static String surl = "http://localhost:8080/number?wsdl";
	private Numbers numbers;
	public ConsumeNumbers() {
		try {
			URL url = new URL(surl);
			QName qname = new QName("http://localhost:8080/number", 
									"ServeNumbersService");
			Service service = Service.create(url, qname);
			numbers = service.getPort(Numbers.class);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	public long next() {
		return numbers.next();
	}

	public static void main(String[] args) {
		final ConsumeNumbers consumeNumbers = new ConsumeNumbers();
		for (int i=0; i<17; i++) {
		    long number = consumeNumbers.next();
			System.out.format("%d ",  number);
			System.out.flush();
		}
		System.out.println("done");
	}
}