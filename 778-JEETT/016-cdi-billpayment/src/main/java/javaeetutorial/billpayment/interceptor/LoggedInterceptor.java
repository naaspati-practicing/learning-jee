package javaeetutorial.billpayment.interceptor;

import java.io.Serializable;

import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Logged
public class LoggedInterceptor implements Serializable {
	private static final long serialVersionUID = -2975194676392609908L;
	
	public Object logMethodEntry(InvocationContext ct) throws Exception {
		System.out.printf("Entring method: %s.%s()\n", ct.getMethod().getDeclaringClass().getName(), ct.getMethod().getName());
		return ct.proceed();
	}
}
