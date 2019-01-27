package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, Address> address;
	public static volatile SingularAttribute<Customer, Long> accountNum;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SetAttribute<Customer, PhoneNumber> phones;
	public static volatile CollectionAttribute<Customer, Order> orders;
	public static volatile SingularAttribute<Customer, Integer> id;
	public static volatile SingularAttribute<Customer, Long> version;

	public static final String ADDRESS = "address";
	public static final String ACCOUNT_NUM = "accountNum";
	public static final String NAME = "name";
	public static final String PHONES = "phones";
	public static final String ORDERS = "orders";
	public static final String ID = "id";
	public static final String VERSION = "version";

}

