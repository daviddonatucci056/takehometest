package com.branch.takehome.v1.controlleradvise;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;

import com.branch.takehome.v1.exception.InvalidInputException;

@RestControllerAdvice
public class GithubAPIControllerV1Advice {
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<Object> handleConflict(InvalidInputException ex, WebRequest request) {
        String bodyOfResponse = "Invalid input";
        return new ResponseEntity<>(bodyOfResponse + ": " + ex.getMessage(), HttpStatus.BAD_REQUEST);

	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<Object> handleConflict(IOException ex, WebRequest request) {
        String bodyOfResponse = "Internal server error";
        return new ResponseEntity<>(bodyOfResponse + ": " + "Failed to map json response from github.", HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseEntity<Object> handleConflict(HttpClientErrorException ex, WebRequest request) {
        String bodyOfResponse = "Failed to retrieve github data from username";
        
        if(ex.getMessage().contains("404")) {
	        return new ResponseEntity<>(bodyOfResponse + ".", HttpStatus.BAD_REQUEST);
        }
        
        return new ResponseEntity<>(bodyOfResponse + ": " + ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
