package com.nmj.trendingNews.controllers;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmj.trendingNews.domain.TrendingNews;
import com.nmj.trendingNews.service.NewsService;

@RestController
public class TrendingNewsController {
	private final static Logger log = LoggerFactory.getLogger(TrendingNewsController.class);

	@Autowired
	private NewsService newsService;

	// TODO: Replace the default SimpleAsyncTaskExecutor with an AsyncTaskExecutor implementation for Prod
	@RequestMapping("/news")
	public Callable<List<TrendingNews>> getNews() {
		log.info("Getting trending news");
		return () -> newsService.getNews();
	}
}
