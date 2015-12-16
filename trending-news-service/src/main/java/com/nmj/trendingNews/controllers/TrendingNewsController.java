package com.nmj.trendingNews.controllers;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmj.trendingNews.domain.GithubUser;
import com.nmj.trendingNews.service.GitHubService;

@RestController
public class TrendingNewsController {

	@Autowired
	private GitHubService gitHubService;

	// Using Callable, thread returns instantly
	//
	// TODO: Check this with log-out statements!
	//
	@RequestMapping("/github/{person}")
	public Callable<GithubUser> getUser(@PathVariable final String person) {
		return () -> gitHubService.getUser(person);
	}

	//	@RequestMapping("/random")
	//	public Tweet randomFortune() {
	//		final List<Tweet> randomFortunes = repository.randomFortunes(new PageRequest(0, 1));
	//		return randomFortunes.get(0);
	//	}
}
