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
import com.branch.takehome.v1.beans.GithubUserData;
import com.branch.takehome.v1.github.api.GithubUser;
import com.branch.takehome.v1.validation.GithubValidations;

@SpringBootTest
class GithubServiceTest {
	
	@Mock
	private GithubAPIService apiService;
	
	@InjectMocks
	private GithubService service = new GithubService();
	
	@Test
	void testLocalDateGithub() {
		assertNull(GithubValidations.readGithubDate(null));
		
		LocalDateTime testDate = LocalDateTime.of(2025, 12, 1, 10, 2, 16);
		
		assertEquals(testDate, GithubValidations.readGithubDate("2025-12-01T10:02:16Z"));
	}
	
	@Test
	void testBuildGithubUserDataValid() throws IOException {
		LocalDateTime expectedDate = LocalDateTime.of(2025, 12, 1, 10, 2, 16);
		
		GithubUser expectedUser = GithubUser.builder()
				.userName("user")
				.displayName("display")
				.avatar("avatar")
				.geoLocation("location")
				.email("email")
				.url("url")
				.createdAt(expectedDate)
				.build();
		when(apiService.getAPIUser(anyString())).thenReturn(expectedUser);
		
		List<GithubRepo> expectedRepos = new ArrayList<>();
		expectedRepos.add(GithubRepo.builder().name("name1").url("url1").build());
		expectedRepos.add(GithubRepo.builder().name("name2").url("url2").build());

		when(apiService.getAPIRepos(anyString())).thenReturn(expectedRepos);
		
		GithubUserData userData =  service.buildGithubUserData("user");
		
		assertEquals(expectedRepos, userData.getRepos());
		assertEquals("user", userData.getUserName());
		assertEquals("display", userData.getDisplayName());
		assertEquals("avatar", userData.getAvatar());
		assertEquals("location", userData.getGeoLocation());
		assertEquals("email", userData.getEmail());
		assertEquals("url", userData.getUrl());
		assertEquals(expectedDate, userData.getCreatedAt());
	}
	
	@Test
	void testBuildGithubUserDataNullDate() throws IOException {
		GithubUser expectedUser = GithubUser.builder()
				.userName("user")
				.displayName("display")
				.avatar("avatar")
				.geoLocation("location")
				.email("email")
				.url("url")
				.createdAt(null)
				.build();
		when(apiService.getAPIUser(anyString())).thenReturn(expectedUser);
		
		List<GithubRepo> expectedRepos = new ArrayList<>();
		expectedRepos.add(GithubRepo.builder().name("name1").url("url1").build());
		expectedRepos.add(GithubRepo.builder().name("name2").url("url2").build());

		when(apiService.getAPIRepos(anyString())).thenReturn(expectedRepos);
		
		GithubUserData userData =  service.buildGithubUserData("user");
		
		assertEquals(expectedRepos, userData.getRepos());
		assertEquals("user", userData.getUserName());
		assertEquals("display", userData.getDisplayName());
		assertEquals("avatar", userData.getAvatar());
		assertEquals("location", userData.getGeoLocation());
		assertEquals("email", userData.getEmail());
		assertEquals("url", userData.getUrl());
		assertNull(userData.getCreatedAt());
	}
	
	@Test
	void testBuildGithubUserDataNullFields() throws IOException {
		GithubUser expectedUser = GithubUser.builder()
				.userName("user")
				.build();
		when(apiService.getAPIUser(anyString())).thenReturn(expectedUser);
		
		List<GithubRepo> expectedRepos = null;

		when(apiService.getAPIRepos(anyString())).thenReturn(expectedRepos);
		
		GithubUserData userData =  service.buildGithubUserData("user");
		
		assertEquals(expectedRepos, userData.getRepos());
		assertEquals("user", userData.getUserName());
		assertNull(userData.getDisplayName());
		assertNull(userData.getAvatar());
		assertNull(userData.getGeoLocation());
		assertNull(userData.getEmail());
		assertNull(userData.getUrl());
		assertNull(userData.getCreatedAt());
	}
	
	@Test
	void testBuildGithubUserDataNullRepoFields() throws IOException {
		LocalDateTime expectedDate = LocalDateTime.of(2025, 12, 1, 10, 2, 16);
		
		GithubUser expectedUser = GithubUser.builder()
				.userName("user")
				.displayName("display")
				.avatar("avatar")
				.geoLocation("location")
				.email("email")
				.url("url")
				.createdAt(expectedDate)
				.build();
		when(apiService.getAPIUser(anyString())).thenReturn(expectedUser);
		
		List<GithubRepo> expectedRepos = new ArrayList<>();
		expectedRepos.add(GithubRepo.builder().build());
		expectedRepos.add(GithubRepo.builder().name("name2").url("url2").build());
		expectedRepos.add(GithubRepo.builder().name("name3").url("url3").build());

		when(apiService.getAPIRepos(anyString())).thenReturn(expectedRepos);
		
		GithubUserData userData =  service.buildGithubUserData("user");
		
		assertEquals(expectedRepos, userData.getRepos());
		assertEquals("user", userData.getUserName());
		assertEquals("display", userData.getDisplayName());
		assertEquals("avatar", userData.getAvatar());
		assertEquals("location", userData.getGeoLocation());
		assertEquals("email", userData.getEmail());
		assertEquals("url", userData.getUrl());
		assertEquals(expectedDate, userData.getCreatedAt());
	}
	
	@Test
	void testBuildGithubUserDataNullRepos() throws IOException {
		LocalDateTime expectedDate = LocalDateTime.of(2025, 12, 1, 10, 2, 16);
		
		GithubUser expectedUser = GithubUser.builder()
				.userName("user")
				.displayName("display")
				.avatar("avatar")
				.geoLocation("location")
				.email("email")
				.url("url")
				.createdAt(expectedDate)
				.build();
		when(apiService.getAPIUser(anyString())).thenReturn(expectedUser);
		
		List<GithubRepo> expectedRepos = new ArrayList<>();
		expectedRepos.add(GithubRepo.builder().build());

		when(apiService.getAPIRepos(anyString())).thenReturn(expectedRepos);
		
		GithubUserData userData =  service.buildGithubUserData("user");
		
		assertEquals(expectedRepos, userData.getRepos());
		assertEquals("user", userData.getUserName());
		assertEquals("display", userData.getDisplayName());
		assertEquals("avatar", userData.getAvatar());
		assertEquals("location", userData.getGeoLocation());
		assertEquals("email", userData.getEmail());
		assertEquals("url", userData.getUrl());
		assertEquals(expectedDate, userData.getCreatedAt());
	}
	

}
