package com.branch.takehome.v1.beans.github;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GithubRepo {
	private String name;
	private String url;
}
