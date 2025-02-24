package com.branch.takehome.v1.controlleradvise;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GenericControllerV1Advice {
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<Object> handleConflict(NoResourceFoundException ex, WebRequest request) {
        String bodyOfResponse = "Unfortunately you did not find anything.";
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.NOT_FOUND);
	}
	
}
