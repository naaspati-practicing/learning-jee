package sam.learn.jaxb;

import java.io.StringReader;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.internal.oxm.Unmarshaller;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;

/**
 * 
 * EclipseLink supports JSON documents without a root element. By default, if no @XmlRootElement annotation exists, the marshalled JSON document will not have a root element. You can override this behavior (that is omit the root element from the JSON output, even if the @XmlRootElement is specified) by setting the JSON_INCLUDE_ROOT property when marshalling a document,
 *
 *<br><br>
 * in this example User2.class doesnt have \@XmlRootElement thus eclipselink creates json without Root 
 *
 */
public class NoRootJsonTest extends UserBaseTest {

	@Override
	protected Class<? extends IUser> cls() {
		return User2.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected <E> E unmarshal(String xml) throws JAXBException {
		unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
		return (E) unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), cls()).getValue();
	}
}
