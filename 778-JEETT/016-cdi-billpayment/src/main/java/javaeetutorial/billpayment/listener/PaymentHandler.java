package javaeetutorial.billpayment.listener;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Observes;

import javaeetutorial.billpayment.event.PaymentEvent;
import javaeetutorial.billpayment.interceptor.Logged;
import javaeetutorial.billpayment.payment.Credit;
import javaeetutorial.billpayment.payment.Debit;

@Logged
@SessionScoped
public class PaymentHandler implements Serializable {
	private static final long serialVersionUID = -8162273830089556815L;
	
	private static final Logger LOGGER = Logger.getLogger(PaymentHandler.class.getName());
	
	public PaymentHandler() {
		LOGGER.info(() -> "INIT: "+getClass().getName());
	}
	public void creditPayment ( @Observes @Credit PaymentEvent e ) {
		LOGGER.info(() -> "PaymentHandler - Credit Handler: "+e);
		
		// call a specific Credit handler class...
	}
	public void debitPayment ( @Observes @Debit PaymentEvent e ) {
		LOGGER.info(() -> "PaymentHandler - Debit Handler: "+e);
		// call a specific Credit handler class...
	}
	
	
	
	
	

}
