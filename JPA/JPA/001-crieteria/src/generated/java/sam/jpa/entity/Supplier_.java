package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Supplier.class)
public abstract class Supplier_ {

	public static volatile SingularAttribute<Supplier, String> name;
	public static volatile SingularAttribute<Supplier, Integer> id;
	public static volatile SingularAttribute<Supplier, Long> version;
	public static volatile SetAttribute<Supplier, Product> products;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String PRODUCTS = "products";

}

