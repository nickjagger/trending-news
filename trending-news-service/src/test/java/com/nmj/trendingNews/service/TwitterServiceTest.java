package com.nmj.trendingNews.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmj.trendingNews.Application;
import com.nmj.trendingNews.domain.Tweet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TwitterServiceTest {


	@Autowired
	private TwitterService classUnderTest;
	
	@Test
	public void getTweetSuccess() throws Exception {
		// mockServer.expect(requestTo("https://github/users/testUser")) //
		// .andExpect(method(HttpMethod.GET)) //
		// .andRespond(
		// withSuccess("{ \"name\" : \"testUser\", \"blog\" : \"testBlog\"}",
		// MediaType.APPLICATION_JSON));

		final List<Tweet> tweets = classUnderTest.getTweetsForArticle(null);

		// mockServer.verify();
		// assertEquals("testUser", user.getName());
		
//		assertNotNull(articles);
//		Assert.assertTrue(articles.size() == 5);

	}
}
