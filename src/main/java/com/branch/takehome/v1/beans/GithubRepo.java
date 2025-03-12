package com.branch.takehome.v1.beans;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Getter
public class GithubRepo {
	private String name;
	@JsonAlias("html_url")
	private String url;
}
