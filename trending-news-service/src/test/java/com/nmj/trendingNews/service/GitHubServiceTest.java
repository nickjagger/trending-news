package com.nmj.trendingNews.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

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
import org.springframework.web.client.RestTemplate;

import com.nmj.trendingNews.Application;
import com.nmj.trendingNews.domain.GithubUser;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GitHubServiceTest {

	@Configuration
	static class ContextConfiguration {
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Autowired
	private GitHubService classUnderTest;

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@Before
	public void setup() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void getUserSuccess() throws Exception {
		mockServer.expect(requestTo("https://github/users/testUser")) //
				.andExpect(method(HttpMethod.GET)) //
				.andRespond(withSuccess("{ \"name\" : \"testUser\", \"blog\" : \"testBlog\"}", MediaType.APPLICATION_JSON));

		final GithubUser user = classUnderTest.getUser("testUser");

		mockServer.verify();
		assertEquals("testUser", user.getName());
	}

	@Test
	public void getUserReturns403AndFallsbackToDefault() throws Exception {
		mockServer.expect(requestTo("https://github/users/testUser")) //
				.andExpect(method(HttpMethod.GET)) //
				.andRespond(withStatus(FORBIDDEN));

		final GithubUser user = classUnderTest.getUser("testUser");

		mockServer.verify();
		assertEquals("unknown", user.getName());
	}
}
