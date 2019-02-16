package javaeetutorial.billpayment.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import javaeetutorial.billpayment.event.PaymentEvent;
import javaeetutorial.billpayment.interceptor.Logged;

@Named
@SessionScoped
public class PaymentBean implements Serializable {
	private static final long serialVersionUID = -1365090513104415244L;
	
	private final Event<PaymentEvent> creditEvent;
	private final Event<PaymentEvent> debitEvent;

	@NotNull
	private PaymentType paymentOption;
	
	@Digits(integer=10, fraction=2, message="Invalid value")
	private BigDecimal value;
	@NotNull
	private LocalDate date;

	@Inject
	public PaymentBean(@Credit Event<PaymentEvent> creditEvent, @Debit Event<PaymentEvent> debitEvent) {
		this.creditEvent = creditEvent;
		this.debitEvent = debitEvent;
	}
	
	public PaymentType getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(PaymentType paymentOption) {
		this.paymentOption = paymentOption;
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
	
	@Logged
	public String pay() {
		this.setDate(LocalDate.now());
		PaymentEvent pe = new PaymentEvent();
		pe.setDate(date);
		pe.setPaymentType(paymentOption);
		pe.setValue(value);
		
		switch (paymentOption) {
			case CREDIT:
				creditEvent.fire(pe);
				break;
			case DEBIT:
				debitEvent.fire(pe);
				break;
		}
		
		return "response";
	}
	
	@Logged
	public void reset() {
		setValue(BigDecimal.ZERO);
		setPaymentOption(PaymentType.DEBIT);
	}
}
