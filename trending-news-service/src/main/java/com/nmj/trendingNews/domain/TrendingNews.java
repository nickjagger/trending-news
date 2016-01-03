package com.nmj.trendingNews.domain;

import java.util.List;

import com.nmj.trendingNews.domain.guardian.GuardianArticle;
import com.nmj.trendingNews.domain.twitter.Tweet;

public class TrendingNews {

	private final GuardianArticle article;
	private final List<Tweet> tweets;

	public TrendingNews(final GuardianArticle article, final List<Tweet> tweets) {
		this.article = article;
		this.tweets = tweets;
	}

	public GuardianArticle getArticle() {
		return article;
	}

	public List<Tweet> getTweets() {
		return tweets;
	}

}
