package com.nmj.trendingNews.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nmj.trendingNews.domain.GithubUser;

@Service
public class GitHubServiceImpl implements GitHubService {

	private static final Logger log = LoggerFactory.getLogger(GitHubServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;


	@Value("${github.url}" + "${github.endpoints.users}")
	private String githubUsersEndpoint;



	@Override
	@HystrixCommand(fallbackMethod = "fallbackUser")
	public GithubUser getUser(final String user) {
		//		final HystrixRequestContext context = HystrixRequestContext.initializeContext();
		return restTemplate.getForObject(githubUsersEndpoint + user, GithubUser.class);
	}


	@SuppressWarnings("unused")
	//	private GithubUser fallbackUser(final String user, final Throwable e) {
	private GithubUser fallbackUser(final String user) {
		log.error("In fallback method for #getUser. Exception: {}");

		//		HystrixRequestLog.getCurrentRequest();

		final GithubUser defaultUser = new GithubUser();
		defaultUser.setName("unknown");
		defaultUser.setBlog("unknown");
		return defaultUser;
	}

}
