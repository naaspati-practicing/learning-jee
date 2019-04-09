package sam.learn.jaxb.relationship;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertSame;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;

/**
 * see:: file:///C:/Users/Sameer/Documents/MEGAsync/websites/eclipselink/www.eclipse.org/eclipselink/documentation/2.6/solutions/jpatoxml002.html#BEIEEFBA<br>
 * Mapping a Many-to-One Shared Reference Relationship<br><br>
 * \@XmlIDREF<br>
 * \@XmlID<br>
 * @author Sameer
 *
 */
class OneToMany_RelationShipTest  extends Base2 { 

	@Test
	void test() throws JAXBException {
		Employee expected = employee(this);
		Employee actual = unmarshal(marshal(expected));
		
		assertSame(actual.getAddress().getOwner(), actual);
		assertAll(actual.getContactNumber().stream().map(p -> () -> assertSame(actual, p.getOwner())));
	}

	public static Employee employee(Base2 base) {
		Employee e = OneToOne_RelationShipTest.employee(base);
		
		for (int i = 0; i < 10; i++) 
			e.addContactNumber(base.lorem.getPhone());
		
		return e;
	}
}
