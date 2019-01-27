package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Item.class)
public abstract class Item_ {

	public static volatile SingularAttribute<Item, Product> product;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SingularAttribute<Item, Integer> id;
	public static volatile MapAttribute<Item, String, String> photos;

	public static final String PRODUCT = "product";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String PHOTOS = "photos";

}

