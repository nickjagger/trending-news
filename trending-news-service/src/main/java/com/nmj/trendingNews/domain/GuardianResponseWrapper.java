package com.nmj.trendingNews.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuardianResponseWrapper {
	private GuardianResponse response;

	public GuardianResponse getResponse() {
		return response;
	}

	public void setResponse(final GuardianResponse response) {
		this.response = response;
	}

}
