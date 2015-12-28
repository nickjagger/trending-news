package com.nmj.trendingNews.domain.guardian;

public enum DefaultGuardianArticle {

	UNAVAILBLE("Guardian service is currently unavailable");

	String webTitle;

	DefaultGuardianArticle(final String webTitle) {
		this.webTitle = webTitle;
	}

	public GuardianArticle getArticle() {
		final GuardianArticle defaultArticle = new GuardianArticle();
		defaultArticle.setWebTitle(webTitle);
		return defaultArticle;
	}
}
