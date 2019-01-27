package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CreditCard.class)
public abstract class CreditCard_ {

	public static volatile SingularAttribute<CreditCard, Long> ccNumber;
	public static volatile ListAttribute<CreditCard, CardTransaction> transactionHistory;
	public static volatile SingularAttribute<CreditCard, Customer> customer;

	public static final String CC_NUMBER = "ccNumber";
	public static final String TRANSACTION_HISTORY = "transactionHistory";
	public static final String CUSTOMER = "customer";

}

