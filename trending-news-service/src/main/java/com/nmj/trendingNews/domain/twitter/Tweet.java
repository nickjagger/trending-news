package com.nmj.trendingNews.domain.twitter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.base.MoreObjects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tweet {

	private Long id;
	private String text;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this) //
				.add("id", id) //
				.add("text", text) //
				.toString();
	}
}
