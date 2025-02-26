package com.branch.takehome.v1.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.branch.takehome.v1.beans.GithubRepo;
import com.branch.takehome.v1.beans.GithubUser;
import com.branch.takehome.v1.beans.GithubUserData;
import com.branch.takehome.v1.validation.GithubValidations;

@Service
public class GithubService {
	private static final Logger LOGGER = LoggerFactory.getLogger(GithubService.class);

	@Autowired 
	private GithubAPIService apiService;
	
	public GithubUserData buildGithubUserData(String username) throws IOException {
		LOGGER.info("Building user data");
		
		GithubUser apiUser = apiService.getAPIUser(username);
		
		//make sure we have all valid inputs before calling next call
		LocalDateTime createdAt = GithubValidations.readGithubDate(apiUser.getCreatedAt());
		
		List<GithubRepo> apiRepos = apiService.getAPIRepos(username);
		
		return GithubUserData.builder()
				.userName(apiUser.getUserName())
				.displayName(apiUser.getDisplayName())
				.avatar(apiUser.getAvatar())
				.geoLocation(apiUser.getGeoLocation())
				.email(apiUser.getEmail())
				.url(apiUser.getUrl())
				.createdAt(createdAt)
				.repos(apiRepos)
				.build();
	}
}
