package com.nmj.trendingNews.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TwitterResponse {

	@JsonProperty("statuses")
	private List<Tweet> tweets;

	public List<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(final List<Tweet> tweets) {
		this.tweets = tweets;
	}
}
