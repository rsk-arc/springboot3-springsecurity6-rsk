package com.rsk.security.exception;

public class DatabaseException extends AppRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 752858877580882829L;

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(Throwable ex) {
		super(ex);
	}

}