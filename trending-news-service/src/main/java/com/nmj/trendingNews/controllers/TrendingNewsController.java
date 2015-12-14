package com.nmj.trendingNews.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmj.trendingNews.domain.User;
import com.nmj.trendingNews.service.GitHubService;

@RestController
public class TrendingNewsController {

	//	@Autowired
	//    TwitterRepository repository;

	@Value("${temp.value}")
	private String configValue;

	@Autowired
	private GitHubService gitHubService;

	@RequestMapping("/news/{subject}")
	public User news(@PathVariable final String subject) {

		System.out.println(configValue);

		final User user = gitHubService.getUser(subject);
		//		return repository.findAll();
		return user;
	}

	//	@RequestMapping("/random")
	//	public Tweet randomFortune() {
	//		final List<Tweet> randomFortunes = repository.randomFortunes(new PageRequest(0, 1));
	//		return randomFortunes.get(0);
	//	}
}
