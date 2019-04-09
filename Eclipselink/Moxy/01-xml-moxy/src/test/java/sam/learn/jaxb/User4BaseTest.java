package sam.learn.jaxb;

public class User4BaseTest extends UserBaseTest {
	
	@Override
	protected Class<? extends IUser> cls() {
		return User4.class;
	}
}
