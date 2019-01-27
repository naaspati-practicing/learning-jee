package sam.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Department.class)
public abstract class Department_ {

	public static volatile SingularAttribute<Department, String> name;
	public static volatile SingularAttribute<Department, Integer> id;
	public static volatile CollectionAttribute<Department, Employee> employees;
	public static volatile SingularAttribute<Department, Long> version;
	public static volatile SingularAttribute<Department, Integer> deptno;

	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String EMPLOYEES = "employees";
	public static final String VERSION = "version";
	public static final String DEPTNO = "deptno";

}

