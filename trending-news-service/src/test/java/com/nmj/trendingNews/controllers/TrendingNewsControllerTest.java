package com.nmj.trendingNews.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.common.collect.Lists;
import com.nmj.trendingNews.Application;
import com.nmj.trendingNews.domain.TrendingNews;
import com.nmj.trendingNews.domain.guardian.DefaultGuardianArticle;
import com.nmj.trendingNews.domain.twitter.DefaultTweet;
import com.nmj.trendingNews.service.NewsService;

/**
 * Based on Spring async controller test.
 *
 * @see https://github.com/spring-projects/spring-mvc-showcase/blob/master/src/test/java/org/springframework/samples/mvc/async/DeferredResultControllerTests.
 *      java
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TrendingNewsControllerTest {

	@Mock
	private NewsService newsServiceMock;

	@InjectMocks
	private TrendingNewsController classUnderTest;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest).build();
	}

	@Test
	public void getNewsSuccess() throws Exception {

		final TrendingNews news = new TrendingNews(DefaultGuardianArticle.UNAVAILBLE.getArticle(), Lists.newArrayList(DefaultTweet.UNAVAILBLE.getTweet()));
		when(newsServiceMock.getNews()).thenReturn(Lists.newArrayList(news));

		final MvcResult mvcResult = mockMvc.perform(get("/news")) //
				.andExpect(status().isOk()) //
				.andExpect(request().asyncStarted()) //
				.andReturn();

		mockMvc.perform(asyncDispatch(mvcResult)) //
				.andExpect(status().isOk()) //
				.andExpect(content().contentType("application/json;charset=UTF-8")) //
				.andExpect(content().string(
						"[{\"article\":{\"webTitle\":\"Guardian service is currently unavailable\"},\"tweets\":[{\"text\":\"Twitter service is currently unavailable\"}]}]"));
	}
}
