package com.rsk.security.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionHelper extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { InvalidInputException.class })
	public ResponseEntity<ApiError> handleInvalidInputException(InvalidInputException ex) {
		log.error("Invalid Input Exception: ", ex.getMessage());
		return createResponseException(ex, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(value = { Unauthorized.class })
	public ResponseEntity<ApiError> handleUnauthorizedException(Unauthorized ex) {
		log.error("Unauthorized Exception: ", ex.getMessage());		
		return createResponseException(ex, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(value = { DatabaseException.class })
	public ResponseEntity<ApiError> handleException(DatabaseException ex) {
		log.error("DatabaseException: ", ex.getMessage());
		return createResponseException(ex, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = { ApplicationException.class })
	public ResponseEntity<ApiError> handleException(ApplicationException ex) {
		log.error("ApplicationException: ", ex.getMessage());
		return createResponseException(ex, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private ResponseEntity<ApiError> createResponseException(AppRuntimeException ex, HttpStatus httpStatus) {
		//log.error(getStackTrace(ex));
		ApiError apiError = new ApiError();
		apiError.setStatus(httpStatus.value());
        apiError.setMessage(ex.getMessage());
        apiError.setErrorCode(ex.getLocalizedMessage());
		return new ResponseEntity<ApiError>(apiError, httpStatus);
	}
		
	public String getStackTrace(Exception error) {
		StringBuilder except = null;
		StringWriter errors = new StringWriter();
		try {
			except = new StringBuilder();
			if (!error.getMessage().equalsIgnoreCase(error.getMessage())) {
				except.append("\n\n");
				except.append("EXCEPTION\n");
				except.append(error.getMessage());
			} 
			except.append("\n\n");
			except.append("STACKTRACE\n");
			error.printStackTrace(new PrintWriter(errors));			
			except.append(errors.toString());
		} catch (Exception e) {			
		} finally {
			errors = null;
		}
		return except.toString();
	}
}
