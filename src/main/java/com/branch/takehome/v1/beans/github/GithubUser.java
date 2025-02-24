package com.branch.takehome.v1.beans.github;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GithubUser {
	private String userName;
	private String displayName;
	private String avatar;
	private String geoLocation;
	private String email;
	private String url;
	private String createdAt;
}
