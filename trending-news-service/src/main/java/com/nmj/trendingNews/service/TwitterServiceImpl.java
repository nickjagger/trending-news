package com.nmj.trendingNews.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nmj.trendingNews.domain.GuardianArticle;
import com.nmj.trendingNews.domain.Tweet;
import com.nmj.trendingNews.domain.TwitterAccessToken;
import com.nmj.trendingNews.domain.TwitterResponse;

@Service
public class TwitterServiceImpl implements TwitterService {
	private static final Logger log = LoggerFactory.getLogger(TwitterServiceImpl.class);

	private static final String AUTH_BODY_KEY = "grant_type";
	private static final String AUTH_BODY_VALUE = "client_credentials";

	@Autowired
	private RestTemplate restTemplate;

	@Value("${twitter.url}" + "${twitter.endpoints.auth}")
	private String accessTokenEndpoint;

	@Value("${twitter.url}" + "${twitter.endpoints.search}")
	private String searchEndpoint;

	@Value("${twitter.searchParams}")
	private String searchParams;

	@Value("${twitter.key}")
	private String authKey;

	@Value("${twitter.secret}")
	private String authSecret;

	@Override
	@HystrixCommand(fallbackMethod = "fallbackGetTweetsForArticle")
	public List<Tweet> getTweetsForArticle(final GuardianArticle article) {
		log.info("Getting tweets for article: {}", article);

		final TwitterAccessToken accessToken = getAccessToken();

		// Create headers map
		final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.AUTHORIZATION, Joiner.on(" ").join(accessToken.getTokenType(), accessToken.getToken()));

		final HttpEntity<?> httpEntity = new HttpEntity<>(headers);
		final String searchUrl = buildSearchUrl(article);
		log.debug("search url: {}", searchUrl);

		// Search Twitter
		final ResponseEntity<TwitterResponse> response = restTemplate.exchange(searchUrl, HttpMethod.GET, httpEntity, TwitterResponse.class);
		log.debug("searchTweets response status: {}", response.getStatusCode());

		final List<Tweet> tweets = response.getBody().getTweets();
		return tweets;
	}

	@SuppressWarnings("unused")
	private List<Tweet> fallbackGetTweetsForArticle(final GuardianArticle article) {
		log.error("In fallback method for #getTweetsForArticle with arg [{}]", article);

		final Tweet tweet = new Tweet();
		tweet.setText("Twitter service is currently unavailable");
		return Lists.newArrayList(tweet);
	}

	// TODO: Cache this method
	private TwitterAccessToken getAccessToken() {
		log.info("Retrieving access token");

		// Create the request body as a MultiValueMap
		final MultiValueMap<String, String> authBody = new LinkedMultiValueMap<String, String>();
		authBody.add(AUTH_BODY_KEY, AUTH_BODY_VALUE);

		// Create headers map
		final MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
		headers.add(HttpHeaders.AUTHORIZATION, getAuthHeaderValue());
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED.toString());

		final HttpEntity<?> httpEntity = new HttpEntity<>(authBody, headers);

		// Do post
		final ResponseEntity<TwitterAccessToken> response = restTemplate.exchange(accessTokenEndpoint, HttpMethod.POST, httpEntity, TwitterAccessToken.class);
		log.debug("getAccessToken response status: {}", response.getStatusCode());

		return response.getBody();
	}

	private String getAuthHeaderValue() {
		final String keySecretEncoded;
		try {
			final String keyEncoded = URLEncoder.encode(authKey, StandardCharsets.UTF_8.toString());
			final String secretEncoded = URLEncoder.encode(authSecret, StandardCharsets.UTF_8.toString());

			final String keySecret = Joiner.on(":").join(keyEncoded, secretEncoded);
			keySecretEncoded = Base64.getEncoder().encodeToString(keySecret.getBytes(StandardCharsets.UTF_8.toString()));
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException("Unable to encode key & secret", e);
		}

		return "Basic " + keySecretEncoded;
	}

	// TODO: Parse article and populate "q" param with url encoded words
	private String buildSearchUrl(final GuardianArticle article) {
		final String searchTerms = "sport"; // TODO: this should be the article
											// search terms
		return Joiner.on("").join(searchEndpoint, searchTerms, searchParams);
	}
}
