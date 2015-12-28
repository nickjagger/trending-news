package com.nmj.trendingNews.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterAccessToken {

	@JsonProperty("access_token")
	private String token;

	@JsonProperty("token_type")
	private String tokenType;

	public String getToken() {
		return token;
	}

	public void setToken(final String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(final String tokenType) {
		this.tokenType = tokenType;
	}
}
