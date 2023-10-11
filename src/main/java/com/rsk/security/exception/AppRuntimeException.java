package com.rsk.security.exception;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class AppRuntimeException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 752858877580882829L;

	Date timestamp = new Date();
	String error;
	String trace;
	String message;
	String path;
	
	public AppRuntimeException(String message) {
		super(message);
		this.message = message;
	}
	
	public AppRuntimeException(Throwable cause) {
		super(cause);
		this.trace = cause.getMessage();
		this.message = cause.getMessage();
		cause.printStackTrace();
	}

	public AppRuntimeException(Throwable cause, String message) {
		super(message, cause);
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getError() {
		return error;
	}

	public String getTrace() {
		return trace;
	}

	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setTrace(String trace) {
		this.trace = trace;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}