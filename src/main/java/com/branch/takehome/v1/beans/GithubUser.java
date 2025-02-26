package com.branch.takehome.v1.beans;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
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
