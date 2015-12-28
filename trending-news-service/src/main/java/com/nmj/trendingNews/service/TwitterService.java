package com.nmj.trendingNews.service;

import java.util.List;

import com.nmj.trendingNews.domain.guardian.GuardianArticle;
import com.nmj.trendingNews.domain.twitter.Tweet;

public interface TwitterService {
	
	List<Tweet> getTweetsForArticle(GuardianArticle article);

}
