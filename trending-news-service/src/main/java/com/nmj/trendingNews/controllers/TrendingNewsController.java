package com.nmj.trendingNews.controllers;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmj.trendingNews.domain.GithubUser;
import com.nmj.trendingNews.domain.TrendingNews;
import com.nmj.trendingNews.service.GitHubService;
import com.nmj.trendingNews.service.NewsService;

@RestController
public class TrendingNewsController {
	private final static Logger log = LoggerFactory.getLogger(TrendingNewsController.class);

	@Autowired
	private GitHubService gitHubService;

	@Autowired
	private NewsService newsService;

	// Using Callable, thread returns instantly
	//
	// TODO: Check this with log-out statements!
	//
	@RequestMapping("/github/{person}")
	public Callable<GithubUser> getUser(@PathVariable final String person) {
		log.info("Getting user [{}]", person);
		return () -> gitHubService.getUser(person);
	}

	// TODO: Replace the default SimpleAsyncTaskExecutor with an AsyncTaskExecutor implementation for Prod
	@RequestMapping("/news")
	public Callable<List<TrendingNews>> getNews() {
		log.info("Getting trending news");
		return () -> newsService.getNews();
	}
}
