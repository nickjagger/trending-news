package com.nmj.trendingNews.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmj.trendingNews.domain.TrendingNews;
import com.nmj.trendingNews.domain.guardian.DefaultGuardianArticle;
import com.nmj.trendingNews.domain.guardian.GuardianArticle;
import com.nmj.trendingNews.domain.twitter.Tweet;

@Service
public class NewsServiceImpl implements NewsService {
	private final static Logger log = LoggerFactory.getLogger(NewsServiceImpl.class);

	@Autowired
	private GuardianService guardianService;

	@Autowired
	private TwitterService twitterService;

	@Override
	public List<TrendingNews> getNews() {
		final List<TrendingNews> news = guardianService.getArticles().parallelStream() //
				.map(article -> {
					final List<Tweet> tweets = getTweetsForArticle(article);
					log.debug("Get news returning article {} and tweets {}", article, tweets);
					return new TrendingNews(article, tweets);
				}) //
				.collect(Collectors.toList());

		log.debug("getNews returning {}", news);
		return news;
	}

	private List<Tweet> getTweetsForArticle(final GuardianArticle article) {
		if (DefaultGuardianArticle.UNAVAILBLE.getArticle().equals(article))
			return Collections.<Tweet> emptyList();
		else
			return twitterService.getTweetsForArticle(article);
	}
}
