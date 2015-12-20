package com.nmj.trendingNews.service;

import com.nmj.trendingNews.domain.GithubUser;

public interface GitHubService {

	GithubUser getUser(final String user);
}
