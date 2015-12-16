package com.nmj.trendingNews.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class GithubUser {

	private String name;
	private String blog;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(final String blog) {
		this.blog = blog;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", blog=" + blog + "]";
	}

}