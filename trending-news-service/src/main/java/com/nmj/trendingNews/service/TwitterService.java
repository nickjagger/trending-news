package com.nmj.trendingNews.service;

import java.util.List;

import com.nmj.trendingNews.domain.GuardianArticle;
import com.nmj.trendingNews.domain.Tweet;

public interface TwitterService {
	
	List<Tweet> getTweetsForArticle(GuardianArticle article);

}
