package com.nmj.trendingNews.text;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StopWordKeywordProcessorTest {

	private final StopWordKeywordProcessor classUnderTest = new StopWordKeywordProcessor();

	@Test
	public void extractKeywordsSuccess() throws Exception {
		final String keywords = classUnderTest.extractKeywordPhrase("The cat - in the hat");
		assertEquals("cat hat", keywords);
	}
}
