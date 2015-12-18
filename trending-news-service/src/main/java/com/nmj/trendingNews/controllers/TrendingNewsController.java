package com.nmj.trendingNews.controllers;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmj.trendingNews.domain.GithubUser;
import com.nmj.trendingNews.service.GitHubService;

@RestController
public class TrendingNewsController {

	private final static Logger log = LoggerFactory.getLogger(TrendingNewsController.class);

	@Autowired
	private GitHubService gitHubService;

	// Using Callable, thread returns instantly
	//
	// TODO: Check this with log-out statements!
	//
	@RequestMapping("/github/{person}")
	public Callable<GithubUser> getUser(@PathVariable final String person) {
		log.info("Getting user [{}]", person);
		return () -> gitHubService.getUser(person);
	}
}
