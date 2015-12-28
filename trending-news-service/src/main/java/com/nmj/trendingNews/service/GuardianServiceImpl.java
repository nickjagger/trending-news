package com.nmj.trendingNews.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nmj.trendingNews.domain.guardian.DefaultGuardianArticle;
import com.nmj.trendingNews.domain.guardian.GuardianArticle;
import com.nmj.trendingNews.domain.guardian.GuardianResponseWrapper;

@Service
public class GuardianServiceImpl implements GuardianService {
	private static final Logger log = LoggerFactory.getLogger(GuardianServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Value("${guardian.url}" + "${guardian.endpoints.articles}" + "${guardian.key}")
	private String guardianArticlesEndpoint;

	@Override
	@HystrixCommand(fallbackMethod = "fallbackGetArticles")
	public List<GuardianArticle> getArticles() {
		log.info("Getting articles");
		final GuardianResponseWrapper wrapper = restTemplate.getForObject(guardianArticlesEndpoint, GuardianResponseWrapper.class);

		log.info("Returning [{}] articles", wrapper.getResponse().getArticles().size());
		return wrapper.getResponse().getArticles();
	}

	@SuppressWarnings("unused")
	private List<GuardianArticle> fallbackGetArticles() {
		log.error("In fallback method for #getArticles");
		return Lists.newArrayList(DefaultGuardianArticle.UNAVAILBLE.getArticle());
	}
}
