package com.nmj.trendingNews.domain;

//@Entity
//@Table(name = "tweets")
public class Tweet {

	//    @Id
	//    @GeneratedValue
	private Long id;

	//    @Column(nullable = false)
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
}
