package com.fis.booklibrary.casestudy.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseStatusExceptionHandler  extends ResponseEntityExceptionHandler   {

	@Autowired
	private HttpServletRequest request;
	
	private static final String TIMESTAMP = "timestamp";
	private static final String PATH = "path";
	private static final String STATUS = "status";
	private static final String MESSAGE = "message";
	
	private String message = "Book copies not available for subscription";
	
	@ExceptionHandler({ResponseStatusException.class})
	public ResponseEntity<Object> handleException(ResponseStatusException ex) {
		Map<String, Object> errorAttributes = new HashMap<>();
		errorAttributes.put(TIMESTAMP, new Date());
		errorAttributes.put(PATH, request.getRequestURI());
		errorAttributes.put(STATUS, 422);
		errorAttributes.put(MESSAGE, message);
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorAttributes);		
	}
	
}
