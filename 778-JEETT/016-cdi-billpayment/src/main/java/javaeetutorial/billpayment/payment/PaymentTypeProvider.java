package javaeetutorial.billpayment.payment;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@Named
@ApplicationScoped
public class PaymentTypeProvider implements Serializable {
	private static final long serialVersionUID = 8787002545854119816L;

	public PaymentType[] getValues() {
		return PaymentType.values();
	}
}
