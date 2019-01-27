package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Vendor.class)
public abstract class Vendor_ {

	public static volatile SingularAttribute<Vendor, String> name;
	public static volatile SingularAttribute<Vendor, Integer> id;
	public static volatile SingularAttribute<Vendor, Long> version;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String VERSION = "version";

}

