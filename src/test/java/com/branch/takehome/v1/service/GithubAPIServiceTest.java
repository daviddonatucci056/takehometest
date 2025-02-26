package com.branch.takehome.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.branch.takehome.v1.beans.GithubRepo;
import com.branch.takehome.v1.beans.GithubUser;
import com.branch.takehome.v1.restclient.CustomRestClientBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class GithubAPIServiceTest {
	private ObjectMapper mapper = new ObjectMapper();

	@Mock
	private CustomRestClientBuilder rcBuilder;
	
	@InjectMocks
	private GithubAPIService service = new GithubAPIService();
	
	@Test
	void testGetTextNullSafe() throws IOException {
		JsonNode node = null;
		assertNull(service.getTextNullSafe(node));
		
		String json = "{\"hello\":null}";
		node = mapper.readTree(json);
		assertNull(service.getTextNullSafe(node.get("hello")));

		json = "{\"hello\":\"null\"}";
		node = mapper.readTree(json);
		assertEquals("null", service.getTextNullSafe(node.get("hello")));

		
		json = "{\"hello\":\"world\"}";
		node = mapper.readTree(json);
		assertEquals("world", service.getTextNullSafe(node.get("hello")));
	}
	
	@Test
	void testGetAPIUser() throws IOException, URISyntaxException {
		when(rcBuilder.buildGetClientStringResponse(anyString())).thenReturn(new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("user.json").toURI()))));
		GithubUser user = service.getAPIUser("octocat");
		
		assertEquals("octocat", user.getUserName());
		assertEquals("The Octocat", user.getDisplayName());
		assertEquals("https://avatars.githubusercontent.com/u/583231?v=4", user.getAvatar());
		assertEquals("San Francisco", user.getGeoLocation());
		assertNull(user.getEmail());
		assertEquals("https://github.com/octocat", user.getUrl());
		assertEquals("2011-01-25T18:44:36Z", user.getCreatedAt());
	}
	
	@Test
	void testGetAPIRepos() throws IOException, URISyntaxException {
		when(rcBuilder.buildGetClientStringResponse(anyString())).thenReturn(new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("repos.json").toURI()))));
		List<GithubRepo> repos = service.getAPIRepos("octocat");
		
		assertEquals("boysenberry-repo-1", repos.get(0).getName());
		assertEquals("https://github.com/octocat/boysenberry-repo-1", repos.get(0).getUrl());
		
		assertEquals("git-consortium", repos.get(1).getName());
		assertEquals("https://github.com/octocat/git-consortium", repos.get(1).getUrl());
		
		assertEquals("Spoon-Knife", repos.get(6).getName());
		assertEquals("https://github.com/octocat/Spoon-Knife", repos.get(6).getUrl());
	}
}
