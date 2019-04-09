package sam.learn.jaxb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

import javax.xml.bind.JAXBException;

import org.junit.jupiter.api.Test;

abstract class UserBaseTest extends Base {
	
	@SuppressWarnings("rawtypes")
	@Override
	protected Class[] classes() {
		return array(cls());
	}

	protected abstract Class<? extends IUser> cls();

	@Test
	void basicTest() throws JAXBException, InstantiationException, IllegalAccessException {
		IUser user = user();
		IUser user2 = unmarshal(marshal(user));
		
		System.out.printf("\n%s@%s -> %s@%s\n", user.getClass().getSimpleName(), System.identityHashCode(user), user2.getClass().getSimpleName(), System.identityHashCode(user2));

		assertNotSame(user, user2);
		assertSame(user.getClass(), user2.getClass());
		assertEquals(user.getId(), user2.getId());
		assertEquals(user.getName(), user2.getName());
		assertEquals(user.getSurname(), user2.getSurname());
	}
	
	protected IUser user() throws InstantiationException, IllegalAccessException {
		IUser user = cls().newInstance();
		user.setId(random.nextInt());
		user.setName(lorem.getFirstName());
		user.setSurname(lorem.getLastName());
		
		return user;
	}

	protected String location() {
		return Thread.currentThread().getStackTrace()[2].toString();
	}
}
