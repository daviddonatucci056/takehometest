package com.branch.takehome.v1.validation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

import com.branch.takehome.v1.exception.InvalidInputException;

public class GithubValidations {
	private static final DateTimeFormatter githubDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
	
	private GithubValidations() {
		//singleton
	}
	
	/**
	 * reads a github date in the form yyyy-MM-ddTHH:mm:ssZ
	 * @param date
	 * @return
	 */
	public static LocalDateTime readGithubDate(String date) {
		if(date == null) {
			return null;
		}
		
		return LocalDateTime.parse(date, githubDateFormatter);
	}
	
	/** 
	 * github users names are alphanumeric but can have '-' and up to 39 characters
	 * @param username
	 * @return sanitized username
	 * @throws InvalidInputException
	 */
	public static String validateUserName(String username) throws InvalidInputException {
		if(StringUtils.isNotBlank(username) && username.length() < 40 && StringUtils.isAlphanumeric(username.replace("-", ""))) {
			return username;
		}
		
		throw new InvalidInputException(
			"Please provide a valid user name. "
			+ "A valid user name is less than 40 characters "
			+ "and contains letters, numbers and hiphens."
		);
	}
}
