package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Phone.class)
public abstract class Phone_ {

	public static volatile SingularAttribute<Phone, Vendor> vendor;
	public static volatile SingularAttribute<Phone, String> name;
	public static volatile SingularAttribute<Phone, Integer> id;
	public static volatile SingularAttribute<Phone, Long> version;

	public static final String VENDOR = "vendor";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String VERSION = "version";

}

