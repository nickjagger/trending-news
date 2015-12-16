package com.nmj.trendingNews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nmj.trendingNews.domain.GithubUser;

@Service
public class GitHubService {

	@Autowired
	private RestTemplate restTemplate;


	@Value("${github.url}" + "${github.endpoints.users}")
	private String githubUsersEndpoint;




	@HystrixCommand(fallbackMethod = "fallbackUser")
	public GithubUser getUser(final String user) {
		return restTemplate.getForObject(githubUsersEndpoint + user, GithubUser.class);
	}


	@SuppressWarnings("unused")
	private GithubUser fallbackUser(final String user) {
		System.err.println("In fallback method #fallbackUser. Returning default user.");

		final GithubUser defaultUser = new GithubUser();
		defaultUser.setName("unknown");
		defaultUser.setBlog("unknnown");
		return defaultUser;
	}

}
