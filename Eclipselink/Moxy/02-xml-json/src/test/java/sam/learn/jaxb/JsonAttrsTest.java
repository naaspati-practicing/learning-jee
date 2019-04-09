package sam.learn.jaxb;

import java.util.Collections;
import java.util.Map;

import org.eclipse.persistence.jaxb.JAXBContextProperties;

/**
 * using attribute prefix 
 *
 */
public class JsonAttrsTest extends UserBaseTest {
	
	@Override
	protected Class<? extends IUser> cls() {
		return User.class;
	}
	
	@Override
	protected Map<String, ?> contextProperties() {
		return Collections.singletonMap(JAXBContextProperties.JSON_ATTRIBUTE_PREFIX, "@");
	}
}
