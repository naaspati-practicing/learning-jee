package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ContactInfo.class)
public abstract class ContactInfo_ {

	public static volatile SingularAttribute<ContactInfo, Address> address;
	public static volatile CollectionAttribute<ContactInfo, Phone> phones;
	public static volatile SingularAttribute<ContactInfo, Integer> id;
	public static volatile SingularAttribute<ContactInfo, Long> version;

	public static final String ADDRESS = "address";
	public static final String PHONES = "phones";
	public static final String ID = "id";
	public static final String VERSION = "version";

}

