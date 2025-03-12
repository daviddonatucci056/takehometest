package com.branch.takehome.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.branch.takehome.v1.beans.GithubRepo;
import com.branch.takehome.v1.github.api.GithubUser;
import com.branch.takehome.v1.restclient.GithubRestClientBuilder;

@SpringBootTest
class GithubAPIServiceTest {
	@Mock
	private GithubRestClientBuilder rcBuilder;
	
	@InjectMocks
	private GithubAPIService service = new GithubAPIService();
	
	
	@Test
	void testGetAPIUser() {
		LocalDateTime expectedDate = LocalDateTime.of(2025, 12, 1, 10, 2, 16);

		GithubUser expectedUser = 
				GithubUser.builder()
				.userName("octocat")
				.url("https://github.com/octocat")
				.email(null)
				.avatar("https://avatars.githubusercontent.com/u/583231?v=4")
				.displayName("The Octocat")
				.geoLocation("San Francisco")
				.createdAt(expectedDate)
				.build();
		
		when(rcBuilder.buildGetUserData(anyString())).thenReturn(expectedUser);
		GithubUser user = service.getAPIUser("octocat");
		
		assertEquals("octocat", user.getUserName());
		assertEquals("The Octocat", user.getDisplayName());
		assertEquals("https://avatars.githubusercontent.com/u/583231?v=4", user.getAvatar());
		assertEquals("San Francisco", user.getGeoLocation());
		assertNull(user.getEmail());
		assertEquals("https://github.com/octocat", user.getUrl());
		assertEquals(expectedDate, user.getCreatedAt());
	}
	
	@Test
	void testGetAPIRepos() throws IOException {
		List<GithubRepo> expectedRepos = new ArrayList<>();
		expectedRepos.add(GithubRepo.builder().name("boysenberry-repo-1").url("https://github.com/octocat/boysenberry-repo-1").build());
		expectedRepos.add(GithubRepo.builder().name("git-consortium").url("https://github.com/octocat/git-consortium").build());
		expectedRepos.add(GithubRepo.builder().name("Spoon-Knife").url("https://github.com/octocat/Spoon-Knife").build());

		when(rcBuilder.buildGetRepoData(anyString())).thenReturn(expectedRepos);
		List<GithubRepo> repos = service.getAPIRepos("octocat");
		
		assertEquals("boysenberry-repo-1", repos.get(0).getName());
		assertEquals("https://github.com/octocat/boysenberry-repo-1", repos.get(0).getUrl());
		
		assertEquals("git-consortium", repos.get(1).getName());
		assertEquals("https://github.com/octocat/git-consortium", repos.get(1).getUrl());
		
		assertEquals("Spoon-Knife", repos.get(2).getName());
		assertEquals("https://github.com/octocat/Spoon-Knife", repos.get(2).getUrl());
	}
}
