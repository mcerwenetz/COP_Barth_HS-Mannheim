package a1.t3;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

// That is the interface for the SOAP-WebService.
// One may retrieve a number with next().
// You may change the endpointInterface to include a package name
// if necessary, e.g. endPointInterface="a1.ServeNumbers"; do not
// forget to also change ServeNumbers.
// Do not change this file other than the endpointInterface.

@WebService(targetNamespace="http://localhost:8080/number",
			endpointInterface="a1.ServeNumbers")
@SOAPBinding(style=Style.RPC)
public interface Numbers {
	@WebMethod public long next();
}
