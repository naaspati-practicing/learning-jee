package sam.learn.jaxb;

public class User3BaseTest extends UserBaseTest {
	
	@Override
	protected Class<? extends IUser> cls() {
		return User3.class;
	}
}
