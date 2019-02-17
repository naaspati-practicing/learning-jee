package javaeetutorial.decorators;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logged
public class LoggedInterceptor implements Serializable {
	private static final long serialVersionUID = -2975194676392609908L;
	private static final Logger LOGGER = Logger.getLogger(LoggedInterceptor.class.getName());
	
	@AroundInvoke
	public Object logMethodEntry(InvocationContext ct) throws Exception {
		LOGGER.info(() -> String.format("Entring method: %s.%s()\n", ct.getMethod().getDeclaringClass().getName(), ct.getMethod().getName()));
		return ct.proceed();
	}
}
