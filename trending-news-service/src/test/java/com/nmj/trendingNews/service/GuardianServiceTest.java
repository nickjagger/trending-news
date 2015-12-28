package com.nmj.trendingNews.service;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
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
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import com.nmj.trendingNews.Application;
import com.nmj.trendingNews.domain.GuardianArticle;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class GuardianServiceTest {

	@Configuration
	static class ContextConfiguration {
		@Bean
		public RestTemplate restTemplate() {
			return new RestTemplate();
		}
	}

	@Autowired
	private GuardianService classUnderTest;

	@Autowired
	private RestTemplate restTemplate;

	private MockRestServiceServer mockServer;

	@Before
	public void setup() {
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Test
	public void getArticlesSuccess() throws Exception {

		mockServer.expect(requestTo("http://content.guardianapis.com/search?page-size=5&api-key=guardian-key")) //
				.andExpect(method(HttpMethod.GET)) //
				.andRespond(withSuccess(
						"{ \"response\": {\"status\": \"ok\",\"results\": [{\"type\": \"article\",\"sectionId\": \"commentisfree\",\"webTitle\": \"All of Chicago – not just its police – must see systemic change to save black lives | Mariame Kaba\",\"webPublicationDate\": \"2015-12-28T19:52:49Z\", \"id\": \"commentisfree/2015/dec/28/all-chicago-not-just-police-systemic-change-save-black-lives\",\"webUrl\": \"http://www.theguardian.com/commentisfree/2015/dec/28/all-chicago-not-just-police-systemic-change-save-black-lives\",\"apiUrl\": \"http://content.guardianapis.com/commentisfree/2015/dec/28/all-chicago-not-just-police-systemic-change-save-black-lives\",\"sectionName\": \"Opinion\"}]}}",
						MediaType.APPLICATION_JSON));

		final List<GuardianArticle> articles = classUnderTest.getArticles();

		mockServer.verify();

		assertEquals(1, articles.size());
		assertEquals("All of Chicago – not just its police – must see systemic change to save black lives | Mariame Kaba", articles.get(0).getWebTitle());
	}

	@Test
	public void getArticlesReturnsBadRequestAndFallsbackToDefault() throws Exception {
		mockServer.expect(requestTo("http://content.guardianapis.com/search?page-size=5&api-key=guardian-key")) //
				.andExpect(method(HttpMethod.GET)) //
				.andRespond(MockRestResponseCreators.withBadRequest());

		final List<GuardianArticle> articles = classUnderTest.getArticles();

		mockServer.verify();

		assertEquals(1, articles.size());
		assertEquals("Guardian service is currently unavailable", articles.get(0).getWebTitle());

	}
}
