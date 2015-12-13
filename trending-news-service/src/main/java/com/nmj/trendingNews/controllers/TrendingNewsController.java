package com.nmj.trendingNews.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nmj.trendingNews.domain.Tweet;
import com.nmj.trendingNews.respositories.TwitterRepository;

@RestController
public class TrendingNewsController {

    @Autowired
    TwitterRepository repository;

    @RequestMapping("/fortunes")
    public Iterable<Tweet> tweets() {
        return repository.findAll();
    }

    @RequestMapping("/random")
    public Tweet randomFortune() {
        List<Tweet> randomFortunes = repository.randomFortunes(new PageRequest(0, 1));
        return randomFortunes.get(0);
    }
}
