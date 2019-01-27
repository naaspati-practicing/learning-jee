package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, String> name;
	public static volatile SingularAttribute<Product, Integer> id;
	public static volatile SingularAttribute<Product, String> productType;
	public static volatile SingularAttribute<Product, String> status;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PRODUCT_TYPE = "productType";
	public static final String STATUS = "status";

}

