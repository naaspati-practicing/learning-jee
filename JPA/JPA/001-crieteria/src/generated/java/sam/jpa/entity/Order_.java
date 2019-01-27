package sam.jpa.entity;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile SetAttribute<Order, Item> lineItems;
	public static volatile SingularAttribute<Order, Address> shippingAddress;
	public static volatile SingularAttribute<Order, Integer> id;
	public static volatile SingularAttribute<Order, BigDecimal> totalCost;
	public static volatile SingularAttribute<Order, Customer> customer;

	public static final String LINE_ITEMS = "lineItems";
	public static final String SHIPPING_ADDRESS = "shippingAddress";
	public static final String ID = "id";
	public static final String TOTAL_COST = "totalCost";
	public static final String CUSTOMER = "customer";

}

