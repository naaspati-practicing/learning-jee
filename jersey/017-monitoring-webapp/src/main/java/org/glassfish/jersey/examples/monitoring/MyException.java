package org.glassfish.jersey.examples.monitoring;

public class MyException extends Exception {
	private static final long serialVersionUID = -7540513511319318995L;
	
	
	public MyException() {
		super();
	}
	public MyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	public MyException(String message, Throwable cause) {
		super(message, cause);
	}
	public MyException(String message) {
		super(message);
	}
	public MyException(Throwable cause) {
		super(cause);
	}
}
