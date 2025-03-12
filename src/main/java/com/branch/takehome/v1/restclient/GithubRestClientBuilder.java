package com.branch.takehome.v1.restclient;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.branch.takehome.v1.github.api.GithubRepo;
import com.branch.takehome.v1.github.api.GithubUser;

@Component
public class GithubRestClientBuilder {
	
	public GithubUser buildGetUserData(String uri) {
		RestClient restClient = RestClient.create();
		return restClient.get()
		  .uri(uri)
		  .retrieve()
		  .body(new ParameterizedTypeReference<GithubUser>() {});
	}
	
	public List<GithubRepo> buildGetRepoData(String uri) {
		RestClient restClient = RestClient.create();
		return restClient.get()
		  .uri(uri)
		  .retrieve()
		  .body(new ParameterizedTypeReference<List<GithubRepo>>() {});
	}
}
