package javaeetutorial.billpayment.event;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javaeetutorial.billpayment.payment.PaymentType;

public class PaymentEvent implements Serializable {
	private static final long serialVersionUID = -4269844643186004794L;
	
	public PaymentType paymentType;
	public BigDecimal value;
	public LocalDate date;
	
	@Override
	public String toString() {
		return String.format("%s = $%s at %s", paymentType, value, date);
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
