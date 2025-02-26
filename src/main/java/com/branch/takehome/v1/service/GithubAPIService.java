package com.branch.takehome.v1.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.branch.takehome.v1.beans.GithubRepo;
import com.branch.takehome.v1.beans.GithubUser;
import com.branch.takehome.v1.restclient.CustomRestClientBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GithubAPIService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GithubAPIService.class);
	private ObjectMapper mapper = new ObjectMapper();
	
	private final String uriBase = "https://api.github.com/users";
	
	@Autowired
	private CustomRestClientBuilder rcBuilder;
	
	@Cacheable(value="users")
	public GithubUser getAPIUser(String username) throws IOException {
		LOGGER.info("Calling github api for {}'s user data", username);
		
		String uri = uriBase + "/" + username;

		JsonNode userData = mapper.readTree(rcBuilder.buildGetClientStringResponse(uri));
		
		return GithubUser.builder()
			.userName(getTextNullSafe(userData.get("login")))
			.displayName(getTextNullSafe(userData.get("name")))
			.avatar(getTextNullSafe(userData.get("avatar_url")))
			.geoLocation(getTextNullSafe(userData.get("location")))
			.email(getTextNullSafe(userData.get("email")))
			.url(getTextNullSafe(userData.get("html_url")))
			.createdAt(getTextNullSafe(userData.get("created_at")))
			.build();
	}
	
	
	@Cacheable(value ="repos")
	public List<GithubRepo> getAPIRepos(String username) throws NoSuchElementException, IOException {
		LOGGER.info("Calling github api for {}'s repo data", username);
		
		String uri = uriBase + "/" + username + "/repos";
		
		JsonNode repoData = mapper.readTree(rcBuilder.buildGetClientStringResponse(uri));

		List<GithubRepo> repos = new ArrayList<>();
		if (repoData.isArray()) {
		    for (final JsonNode repoNode : repoData) {
		    	GithubRepo repo = 
		    			GithubRepo.builder()
		    			.name(getTextNullSafe(repoNode.get("name")))
		    			.url(getTextNullSafe(repoNode.get("html_url")))
		    			.build();
		        repos.add(repo);
		    }
		}
		
		return repos;
	}
	
	protected String getTextNullSafe(JsonNode node) {
		if(node == null || node.isNull()) {
			return null;
		}
		
		return node.asText();
	}
}
