package com.nmj.trendingNews.domain.twitter;

public enum DefaultTweet {

	UNAVAILBLE("Twitter service is currently unavailable");

	private final Tweet tweet;

	DefaultTweet(final String text) {
		tweet = new Tweet();
		tweet.setText(text);
	}

	public Tweet getTweet() {
		return tweet;
	}
}
