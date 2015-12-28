package com.nmj.trendingNews.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nmj.trendingNews.domain.GuardianArticle;
import com.nmj.trendingNews.domain.GuardianResponseWrapper;

@Service
public class GuardianServiceImpl implements GuardianService {

	/**
	 *
	 * TODO: Add Hystrix fallback method and unit tests. Add Logging
	 *
	 */

	private static final Logger log = LoggerFactory.getLogger(GuardianServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${guardian.url}" + "${guardian.endpoints.articles}" + "${guardian.key}")
	private String guardianArticlesEndpoint;

	@Override
	// @HystrixCommand(fallbackMethod = "fallbackUser")
	public List<GuardianArticle> getArticles() {
		final GuardianResponseWrapper wrapper = restTemplate.getForObject(guardianArticlesEndpoint, GuardianResponseWrapper.class);
		return wrapper.getResponse().getArticles();
	}
}
