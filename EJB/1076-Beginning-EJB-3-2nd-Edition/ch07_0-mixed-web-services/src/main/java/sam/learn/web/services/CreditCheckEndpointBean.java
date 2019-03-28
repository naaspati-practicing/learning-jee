package sam.learn.web.services;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(serviceName="CreditService")
public class CreditCheckEndpointBean {
	
	@WebMethod(operationName="CreditCheck")
	public boolean validateCC(String cc) {
		return true;
	}

}
