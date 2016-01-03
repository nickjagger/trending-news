package com.nmj.trendingNews.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.nmj.trendingNews.domain.TrendingNews;
import com.nmj.trendingNews.domain.guardian.DefaultGuardianArticle;
import com.nmj.trendingNews.domain.guardian.GuardianArticle;
import com.nmj.trendingNews.domain.twitter.Tweet;

@RunWith(MockitoJUnitRunner.class)
public class NewsServiceImplTest {

	@Mock
	private GuardianService guardianServiceMock;

	@Mock
	private TwitterService twitterServiceMock;

	@InjectMocks
	private NewsServiceImpl classUnderTest;

	@Test
	public void getTrendingNewsSuccess() throws Exception {

		final GuardianArticle sportArticle = new GuardianArticle();
		final Tweet sportTweet = new Tweet();

		final GuardianArticle techArticle = new GuardianArticle();
		final Tweet techTweet1 = new Tweet();
		final Tweet techTweet2 = new Tweet();

		when(guardianServiceMock.getArticles()).thenReturn(Lists.newArrayList(sportArticle, techArticle));

		when(twitterServiceMock.getTweetsForArticle(sportArticle)).thenReturn(Lists.newArrayList(sportTweet));
		when(twitterServiceMock.getTweetsForArticle(techArticle)).thenReturn(Lists.newArrayList(techTweet1, techTweet2));

		final List<TrendingNews> news = classUnderTest.getNews();

		assertEquals(2, news.size());

		final Map<GuardianArticle, List<Tweet>> tweetsByArticle = news.stream() //
				.collect(Collectors.toMap(TrendingNews::getArticle, TrendingNews::getTweets));

		final List<Tweet> sportTweets = tweetsByArticle.get(sportArticle);
		assertTrue(sportTweets.contains(sportTweet));

		final List<Tweet> techTweets = tweetsByArticle.get(techArticle);
		assertTrue(techTweets.containsAll(Lists.newArrayList(techTweet1, techTweet2)));

	}

	@Test
	public void twitterNotCalledWhenGuardianFails() throws Exception {

		when(guardianServiceMock.getArticles()).thenReturn(Lists.newArrayList(DefaultGuardianArticle.UNAVAILBLE.getArticle()));

		final List<TrendingNews> news = classUnderTest.getNews();
		assertEquals(1, news.size());
		assertEquals(DefaultGuardianArticle.UNAVAILBLE.getArticle(), news.get(0).getArticle());

		verify(twitterServiceMock, never()).getTweetsForArticle(any(GuardianArticle.class));
	}

}
