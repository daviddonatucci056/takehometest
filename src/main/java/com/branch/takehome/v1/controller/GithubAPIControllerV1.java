package com.branch.takehome.v1.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.branch.takehome.v1.beans.GithubUserData;
import com.branch.takehome.v1.exception.InvalidInputException;
import com.branch.takehome.v1.service.GithubService;
import com.branch.takehome.v1.validation.GithubValidations;

@RestController
@RequestMapping("github-api/v1")
public class GithubAPIControllerV1 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GithubAPIControllerV1.class);
	
	@Autowired
	private GithubService githubService;

	@GetMapping("/userdata/{username}")
	public GithubUserData getGithubData(@PathVariable String username) throws InvalidInputException, IOException {
		LOGGER.info("Getting github user info");
		//validate
		String validatedUsername = GithubValidations.validateUserName(username);
		
		LOGGER.info("Requesting github data for user {} ", validatedUsername);

		GithubUserData userData = githubService.buildGithubUserData(validatedUsername);
		
		return userData;
	}
}
