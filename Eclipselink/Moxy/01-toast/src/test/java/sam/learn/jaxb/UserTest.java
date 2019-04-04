package sam.learn.jaxb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Objects;
import java.util.Random;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Marshaller.Listener;
import javax.xml.bind.Unmarshaller;

import org.eclipse.persistence.jaxb.JAXBContext;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.thedeanda.lorem.LoremIpsum;

class UserTest {
	static JAXBContext context;
	@BeforeAll
	static void init() throws JAXBException {
		context = (JAXBContext) JAXBContext.newInstance(User.class);
	}
	
	LoremIpsum lorem;
	Random r;
	Marshaller marshaller;
	Unmarshaller unmarshaller ;
	
	@BeforeEach
	void init2() throws JAXBException  {
		lorem = LoremIpsum.getInstance();
		r = new Random();
		
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		unmarshaller = context.createUnmarshaller();
	}

	@Test
	void basicTest() throws JAXBException {
		User user = user();

		StringWriter sw = new StringWriter();
		marshaller.setListener(marshallListener());

		marshaller.marshal(user, sw);

		String xml = sw.toString();
		unmarshaller.setListener(unmarshallerListener());
		
		User user2 = (User) unmarshaller.unmarshal(new StringReader(xml));

		System.out.println(xml);

		assertNotSame(user, user2);
		assertEquals(user.getId(), user2.getId());
		assertEquals(user.getName(), user2.getName());
		assertEquals(user.getSurname(), user2.getSurname());
	}
	
	private User user() {
		return new User(r.nextInt(), lorem.getFirstName(), lorem.getLastName());
	}

	@Test
	void xPathTest() throws JAXBException {
		User user = user();
		
		assertEquals(Integer.valueOf(user.getId()), context.getValueByXPath(user, "@id", null, int.class));
		assertEquals(user.getName(), context.getValueByXPath(user, "name/text()", null, String.class));
		assertEquals(user.getSurname(), context.getValueByXPath(user, "surname/text()", null, String.class));
		
		User user2 = user();
		
		System.out.println(user+"\n"+user2);
		
		assertNotSame(user, user2);
		assertNotEquals(user.getId(),      user2.getId());
		assertNotEquals(user.getName(),    user2.getName());
		assertNotEquals(user.getSurname(), user2.getSurname());
		
		context.setValueByXPath(user, "@id", null, user2.getId());
		context.setValueByXPath(user, "name/text()", null, user2.getName());
		context.setValueByXPath(user, "surname/text()", null, user2.getSurname());
		
		assertNotSame(user, user2);
		assertEquals(user.getId(),      user2.getId());
		assertEquals(user.getName(),    user2.getName());
		assertEquals(user.getSurname(), user2.getSurname());
	}

	private Unmarshaller.Listener unmarshallerListener() {
		return new Unmarshaller.Listener() {
			@Override
			public void afterUnmarshal(Object target, Object parent) {
				System.out.println("["+location()+"] target: "+target+",  parent: "+parent);
			}
			@Override
			public void beforeUnmarshal(Object target, Object parent) {
				System.out.println("["+location()+"] target: "+target+",  parent: "+parent);
			}
		};
	}

	private Listener marshallListener() {
		return new Listener() {
			@Override
			public void afterMarshal(Object source) {
				System.out.println("["+location()+"] "+source);
			}
			@Override
			public void beforeMarshal(Object source) {
				System.out.println("["+location()+"] "+source);
			}
		};
	}

	protected String location() {
		return Thread.currentThread().getStackTrace()[2].toString();
	}
}
