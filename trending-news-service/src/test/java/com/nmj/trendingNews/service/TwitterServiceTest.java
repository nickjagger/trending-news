package com.nmj.trendingNews.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.web.client.RestTemplate;

import com.nmj.trendingNews.Application;
import com.nmj.trendingNews.domain.Tweet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TwitterServiceTest {

	@Configuration
	static class ContextConfiguration {
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Autowired
	private TwitterService classUnderTest;

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@Before
	public void setup() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void getTweetsSuccess() throws Exception {
		mockServer.expect(requestTo("https://api.twitter.com:443/oauth2/token")) //
				.andExpect(header("Authorization", "Basic dHdpdHRlci1rZXk6dHdpdHRlci1zZWNyZXQ=")) //
				.andExpect(header("Content-Type", "application/x-www-form-urlencoded")) //
				.andExpect(MockRestRequestMatchers.content().string("grant_type=client_credentials")) //
				.andExpect(method(HttpMethod.POST)) //
				.andRespond(withSuccess("{ \"token_type\" : \"bearer\", \"access_token\" : \"TOKEN\"}", MediaType.APPLICATION_JSON));

		mockServer.expect(requestTo("https://api.twitter.com:443/1.1/search/tweets.json?q=sport&lang=en&result_type=recent&count=5")) //
				.andExpect(header("Authorization", "bearer TOKEN")) //
				.andExpect(method(HttpMethod.GET)) //
				.andRespond(withSuccess(
						"{\"statuses\": [{\"created_at\": \"Mon Dec 28 15:25:40 +0000 2015\",\"id\": 681496241453318144,\"text\": \"test tweet text\"}]}",
						MediaType.APPLICATION_JSON));

		final List<Tweet> tweets = classUnderTest.getTweetsForArticle(null);

		mockServer.verify();

		assertEquals(1, tweets.size());
		final Tweet tweet = tweets.get(0);
		assertEquals("test tweet text", tweet.getText());
		assertEquals(Long.valueOf(681496241453318144L), tweet.getId());
	}

	@Test
	public void getAccessTokenReturnsForbiddenAndFallsbackToDefault() throws Exception {
		mockServer.expect(requestTo("https://api.twitter.com:443/oauth2/token")) //
				.andRespond(withStatus(FORBIDDEN));

		final List<Tweet> tweets = classUnderTest.getTweetsForArticle(null);

		mockServer.verify();

		assertEquals(1, tweets.size());
		final Tweet tweet = tweets.get(0);
		assertEquals("Twitter service currently unavailable", tweet.getText());
	}

	@Test
	public void getTweetsReturnsForbiddenAndFallsbackToDefault() throws Exception {
		mockServer.expect(requestTo("https://api.twitter.com:443/oauth2/token")) //
				.andExpect(method(HttpMethod.POST)) //
				.andRespond(withSuccess("{ \"token_type\" : \"bearer\", \"access_token\" : \"TOKEN\"}", MediaType.APPLICATION_JSON));

		mockServer.expect(requestTo("https://api.twitter.com:443/1.1/search/tweets.json?q=sport&lang=en&result_type=recent&count=5")) //
				.andExpect(header("Authorization", "bearer TOKEN")) //
				.andExpect(method(HttpMethod.GET)) //
				.andRespond(withStatus(FORBIDDEN));

		final List<Tweet> tweets = classUnderTest.getTweetsForArticle(null);

		mockServer.verify();

		assertEquals(1, tweets.size());
		final Tweet tweet = tweets.get(0);
		assertEquals("Twitter service is currently unavailable", tweet.getText());
	}

}
