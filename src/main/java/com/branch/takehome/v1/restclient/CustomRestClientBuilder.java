package com.branch.takehome.v1.restclient;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CustomRestClientBuilder {
	
	public String buildGetClientStringResponse(String uri) {
		RestClient restClient = RestClient.create();
		return restClient.get()
		  .uri(uri)
		  .retrieve()
		  .body(String.class);
	}
}
