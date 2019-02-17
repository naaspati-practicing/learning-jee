import javax.inject.Singleton;

import org.glassfish.jersey.internal.inject.AbstractBinder;

public class StoreBinder extends AbstractBinder {

	@Override
	protected void configure() {
		bind(Store.class).to(Store.class).in(Singleton.class);
	}

}
