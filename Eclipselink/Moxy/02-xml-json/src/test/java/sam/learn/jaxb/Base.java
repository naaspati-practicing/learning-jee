package sam.learn.jaxb;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.JAXBContext;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.eclipse.persistence.oxm.MediaType;
import org.junit.jupiter.api.BeforeEach;

import com.thedeanda.lorem.LoremIpsum;

public abstract class Base {
	public LoremIpsum lorem;
	public Random random;
	public Marshaller marshaller;
	public Unmarshaller unmarshaller ;
	public JAXBContext context;
	
	@BeforeEach
	void init2() throws JAXBException  {
		context = (JAXBContext) JAXBContext.newInstance(classes(), contextProperties());
		
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.setProperty(MarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
		
		unmarshaller = context.createUnmarshaller();
		unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, MediaType.APPLICATION_JSON);
		
		lorem = LoremIpsum.getInstance();
		random = new Random();
	}
	
	protected Map<String, ?> contextProperties() {
		return Collections.emptyMap();
	}
	@SuppressWarnings("rawtypes")
	protected abstract Class[] classes();
	@SuppressWarnings("rawtypes")
	protected Class[] array(Class...classes) {
		return classes;
	}
	

	protected String marshal(Object expected) throws JAXBException {
		StringWriter sw = new StringWriter();
		marshaller.marshal(expected, sw);
		
		String xml = sw.toString();
		System.out.println(xml);
		return xml;
	}
	
	@SuppressWarnings("unchecked")
	protected <E> E unmarshal(String xml) throws JAXBException {
		return (E) unmarshaller.unmarshal(new StringReader(xml));
	}
}
