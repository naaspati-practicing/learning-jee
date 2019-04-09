package sam.learn.jaxb;

import javax.xml.bind.JAXBException;

import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.junit.jupiter.api.Test;


/**
 * 
 * User.class has \@XmlRootElement, using Marshaller property to disable root
 *
 */
public class NoRootJson2Test extends NoRootJsonTest {
	@Override
	protected Class<? extends IUser> cls() {
		return User.class;
	}

	@Test
	@Override
	void basicTest() throws JAXBException, InstantiationException, IllegalAccessException {
		marshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
		super.basicTest();
	}

}
