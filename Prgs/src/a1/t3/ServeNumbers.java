package a1.t3;

import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.Endpoint;

// Run ServeNumbers as is independently. It serves the 
// numbers from 1 to MAX as SOAP-WebService on port 8080.
// Each request will take 50-150 ms to complete.
// Use an instance of ConsumeNumbers to consume a number.
// You may change the endpointInterface to include a package name
// if necessary, e.g. endPointInterface="ex3.Numbers"; do not
// forget to also change Numbers.
// Do not change this file other than the endpointInterface.

@WebService(targetNamespace="http://localhost:8080/number",
			endpointInterface="a1.Numbers")
@SOAPBinding(style=Style.RPC)
public class ServeNumbers implements Numbers {
	private final static int MAX = 10_000;

	private AtomicLong number;
	private Random random;
	private long max;
	private ServeNumbers(int max) {
		number = new AtomicLong(0);
		random = new Random();
		this.max = max;
	}

	@Override
	public long next() {
		long ret = number.incrementAndGet();
		try {
			int sleepTime = 50 + random.nextInt(101);
			Thread.sleep(sleepTime); // sleep 50-150 ms, serve slowly
		} catch (InterruptedException ignore) {
		}
		return ret > max ? 0 : ret;
	}

	public static void main(String[] args) {
		// serve MAX numbers from 1 to MAX
		Endpoint.publish("http://localhost:8080/number", 
						 new ServeNumbers(MAX));
	}
}
