package sam.learn.jaxb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.Marshaller.Listener;

import org.junit.jupiter.api.Test;

public class UserTest extends UserBaseTest {

	@Override
	protected Class<? extends IUser> cls() {
		return User.class;
	}
	
	@Test
	@Override
	void basicTest() throws JAXBException, InstantiationException, IllegalAccessException {
		marshaller.setListener(marshallListener());
		unmarshaller.setListener(unmarshallerListener());
		
		super.basicTest();
	}
	
	@Test
	void xPathTest() throws JAXBException, InstantiationException, IllegalAccessException {
		User user = (User) user();
		
		assertEquals(Integer.valueOf(user.getId()), context.getValueByXPath(user, "@id", null, int.class));
		assertEquals(user.getName(), context.getValueByXPath(user, "name/text()", null, String.class));
		assertEquals(user.getSurname(), context.getValueByXPath(user, "surname/text()", null, String.class));
		
		User user2 = (User) user();
		
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

}
