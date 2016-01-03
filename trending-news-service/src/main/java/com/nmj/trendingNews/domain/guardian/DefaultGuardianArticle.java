package com.nmj.trendingNews.domain.guardian;

public enum DefaultGuardianArticle {

	UNAVAILBLE("Guardian service is currently unavailable");

	private final GuardianArticle article;

	DefaultGuardianArticle(final String webTitle) {
		article = new GuardianArticle();
		article.setWebTitle(webTitle);
	}

	public GuardianArticle getArticle() {
		return article;
	}
}
