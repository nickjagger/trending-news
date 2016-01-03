package com.nmj.trendingNews.text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Crude text processor to extract keywords from an article by removing English stop words.
 */
@Component
public class StopWordKeywordProcessor implements KeywordProcessor {
	private static final Logger log = LoggerFactory.getLogger(StopWordKeywordProcessor.class);

	private static final String STOPWORDS_FILE = "/stopwords.txt";
	private final List<String> stopWords;

	public StopWordKeywordProcessor() {
		try {
			stopWords = Files.readAllLines(Paths.get(this.getClass().getResource(STOPWORDS_FILE).toURI()));
		} catch (final Exception e) {
			throw new RuntimeException("Unable to initialise stop words", e);
		}
	}

	@Override
	public String extractKeywordPhrase(final String text) {
		final String keywords = Stream.of(text.split(" ")) //
				.map(String::toLowerCase) //
				.filter(s -> !stopWords.contains(s)) //
				.map(this::urlEncode) //
				.collect(Collectors.joining(" "));

		log.debug("text [{}] produced keywords [{}]", text, keywords);
		return keywords;

	}

	private String urlEncode(final String input) {
		try {
			return URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
		} catch (final UnsupportedEncodingException e) {
			throw new RuntimeException("Unable to encode search term, UTF-8 is unsupported", e);
		}
	}

}
