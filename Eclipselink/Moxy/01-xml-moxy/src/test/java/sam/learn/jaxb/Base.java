package sam.learn.jaxb;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Random;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.JAXBContext;
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
		context = (JAXBContext) JAXBContext.newInstance(classes());
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		unmarshaller = context.createUnmarshaller();
		
		lorem = LoremIpsum.getInstance();
		random = new Random();
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
