package com.nmj.trendingNews.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nmj.trendingNews.domain.User;

@RestController
public class TrendingNewsController {

	//	@Autowired
	//    TwitterRepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/news/{subject}")
	public User news(@PathVariable final String subject) {
		final User user = restTemplate.getForObject("https://github/users/" + subject, User.class);
		//		return repository.findAll();
		return user;
	}

	//	@RequestMapping("/random")
	//	public Tweet randomFortune() {
	//		final List<Tweet> randomFortunes = repository.randomFortunes(new PageRequest(0, 1));
	//		return randomFortunes.get(0);
	//	}
}
