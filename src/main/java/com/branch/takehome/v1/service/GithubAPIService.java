package com.branch.takehome.v1.service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.branch.takehome.v1.beans.GithubRepo;
import com.branch.takehome.v1.github.api.GithubUser;
import com.branch.takehome.v1.restclient.GithubRestClientBuilder;

@Service
public class GithubAPIService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GithubAPIService.class);
	
	private final String uriBase = "https://api.github.com/users";
	
	@Autowired
	private GithubRestClientBuilder rcBuilder;
	
	@Cacheable(value="users")
	public GithubUser getAPIUser(String username) {
		LOGGER.info("Calling github api for {}'s user data", username);
		
		String uri = uriBase + "/" + username;

		return rcBuilder.buildGetUserData(uri);
	}
	
	
	@Cacheable(value ="repos")
	public List<GithubRepo> getAPIRepos(String username) throws NoSuchElementException, IOException {
		LOGGER.info("Calling github api for {}'s repo data", username);
		
		String uri = uriBase + "/" + username + "/repos";
		
		return rcBuilder.buildGetRepoData(uri);
	}
}
