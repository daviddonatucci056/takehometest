package com.branch.takehome.v1.validation;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.branch.takehome.v1.exception.InvalidInputException;

public class GithubValidations {
	
	// starts with alphanumeric, 
	//captures alphanumeric or - with a look ahead to make sure the next character is a alphanumeric
	// do that 0 to 38 times for a total of 39 characters
	private static final Pattern USER_NAME_PATTERN = Pattern.compile("^[a-zA-Z\\d](?:[a-zA-Z\\d]|-(?=[a-zA-Z\\d])){0,38}$");
	
	private GithubValidations() {
		//singleton
	}
	
	/** 
	 * github users names are alphanumeric but can have '-' and up to 39 characters
	 * @param username
	 * @return sanitized username
	 * @throws InvalidInputException
	 */
	public static String validateUserName(String username) throws InvalidInputException {
		if(StringUtils.isNotBlank(username) && USER_NAME_PATTERN.matcher(username).find()) { 
			return username;
		}
		
		throw new InvalidInputException(
			"Please provide a valid user name. "
			+ "A valid user name is less than 40 characters "
			+ "and contains letters, numbers and hiphens."
		);
	}
}
