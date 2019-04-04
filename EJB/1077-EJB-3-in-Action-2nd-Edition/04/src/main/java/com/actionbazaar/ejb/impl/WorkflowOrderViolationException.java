package com.actionbazaar.ejb.impl;

public class WorkflowOrderViolationException extends RuntimeException {
	private static final long serialVersionUID = -3678570384572339736L;

	public WorkflowOrderViolationException() {
		super();
	}

	public WorkflowOrderViolationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WorkflowOrderViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public WorkflowOrderViolationException(String message) {
		super(message);
	}

	public WorkflowOrderViolationException(Throwable cause) {
		super(cause);
	}
	
	

}
