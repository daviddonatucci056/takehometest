package com.branch.takehome.v1.beans;

import java.time.LocalDateTime;
import java.util.List;

import com.branch.takehome.v1.github.api.GithubRepo;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
@Getter
public class GithubUserData {
	@JsonAlias("user_name")
	private String userName;
	
	@JsonAlias("display_name")
	private String displayName;
	
	private String avatar;
	
	@JsonAlias("geo_location")
	private String geoLocation;
	
	private String email;
	private String url;
	
	@JsonAlias("created_at")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	private List<GithubRepo> repos;
}
