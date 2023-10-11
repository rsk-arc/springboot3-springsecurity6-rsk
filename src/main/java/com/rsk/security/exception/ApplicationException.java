package com.rsk.security.exception;
public class ApplicationException extends AppRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 752858877580882829L;

	public ApplicationException(String message) {
		super(message);
	}
	
	public ApplicationException(Throwable ex) {
		super(ex);
	}

}