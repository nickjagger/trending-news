package com.nmj.trendingNews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nmj.trendingNews.domain.User;

@Service
public class GitHubService {

	@Autowired
	private RestTemplate restTemplate;


	@HystrixCommand(fallbackMethod = "fallbackUser")
	public User getUser(final String user) {
		return restTemplate.getForObject("https://github/users/" + user, User.class);
	}


	@SuppressWarnings("unused")
	private User fallbackUser(final String user) {
		final User defaultUser = new User();
		defaultUser.setName("unknown");
		defaultUser.setBlog("unknnown");
		return defaultUser;
	}

}
