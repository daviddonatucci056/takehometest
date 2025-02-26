package com.branch.takehome.v1.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Getter
public class GithubRepo {
	private String name;
	private String url;
}
