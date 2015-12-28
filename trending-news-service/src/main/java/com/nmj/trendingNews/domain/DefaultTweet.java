package com.nmj.trendingNews.domain;

public enum DefaultTweet {

	UNAVAILBLE("Twitter service is currently unavailable");

	String text;

	DefaultTweet(final String text) {
		this.text = text;
	}

	public Tweet getTweet() {
		final Tweet defaultTweet = new Tweet();
		defaultTweet.setText(text);
		return defaultTweet;
	}
}
