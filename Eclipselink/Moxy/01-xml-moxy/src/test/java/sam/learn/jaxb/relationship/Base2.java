package sam.learn.jaxb.relationship;

import sam.learn.jaxb.Base;

class Base2 extends Base {
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Class[] classes() {
		return array(Employee.class, Address.class, PhoneNumber.class);
	}

}
