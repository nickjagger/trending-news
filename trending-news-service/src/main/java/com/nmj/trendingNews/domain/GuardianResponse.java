package com.nmj.trendingNews.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuardianResponse {
	private String status;
	@JsonProperty("results")
	private List<GuardianArticle> articles;

	public String getStatus() {
		return status;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public List<GuardianArticle> getArticles() {
		return articles;
	}

	public void setArticles(final List<GuardianArticle> articles) {
		this.articles = articles;
	}
}
