package com.nmj.trendingNews.domain;

import javax.persistence.*;

@Entity
@Table(name = "tweets")
public class Tweet {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
