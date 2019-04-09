package sam.learn.jaxb.relationship;

import static org.junit.jupiter.api.Assertions.assertSame;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;

import sam.learn.jaxb.Base;

class OneToOne_RelationShipTest extends Base2 {

	@Test
	void test() throws JAXBException {
		Employee expected = employee(this);	
		Employee actual = unmarshal(marshal(expected));
		
		assertSame(actual.getAddress().getOwner(), actual);
	}

	public static Employee employee(Base2 base) {
		Employee e = new Employee();
		e.setId(10);
		
		Address address = new Address();
		address.setCity(base.lorem.getCity());
		address.setHouseNumber("houseNumber");
		address.setState(base.lorem.getStateFull());
		address.setZipcode(base.lorem.getZipCode());
		
		e.setAddress(address);
		
		e.setName(base.lorem.getFirstName());
		e.setSurname(base.lorem.getLastName());
		e.setSalary(base.random.nextDouble() * 10000);
		
		assertSame(address, e.getAddress());
		assertSame(e, address.getOwner());
		
		return e;
	}
	
}
