package com.nmj.trendingNews.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nmj.trendingNews.Application;
import com.nmj.trendingNews.domain.GuardianArticle;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GuardianServiceImplTest {

	// @Configuration
	// static class ContextConfiguration {
	// @Bean
	// public RestTemplate restTemplate() {
	// return new RestTemplate();
	// }
	// }

	@Autowired
	private GuardianService classUnderTest;

	// @Autowired
	// private RestTemplate restTemplate;
	//
	// private MockRestServiceServer mockServer;

	@Before
	public void setup() {
		// mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void getUserSuccess() throws Exception {
		// mockServer.expect(requestTo("https://github/users/testUser")) //
		// .andExpect(method(HttpMethod.GET)) //
		// .andRespond(
		// withSuccess("{ \"name\" : \"testUser\", \"blog\" : \"testBlog\"}",
		// MediaType.APPLICATION_JSON));

		final List<GuardianArticle> articles = classUnderTest.getArticles();

		// mockServer.verify();
		// assertEquals("testUser", user.getName());
		assertNotNull(articles);
		Assert.assertTrue(articles.size() == 5);

		System.out.println(articles);
	}

	// @Test
	// public void getUserThrows403AndFallsbackToDefault() throws Exception {
	// mockServer.expect(requestTo("https://github/users/testUser")) //
	// .andExpect(method(HttpMethod.GET)) //
	// .andRespond(withStatus(FORBIDDEN));
	//
	// final GithubUser user = classUnderTest.getUser("testUser");
	//
	// mockServer.verify();
	// assertEquals("unknown", user.getName());
	// }
}
