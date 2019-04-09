package sam.learn.jaxb;

public class User2BaseTest extends UserBaseTest {
	
	@Override
	protected Class<? extends IUser> cls() {
		return User2.class;
	}
}
