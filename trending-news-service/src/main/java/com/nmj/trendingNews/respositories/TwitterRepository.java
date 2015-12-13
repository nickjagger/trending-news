package com.nmj.trendingNews.respositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nmj.trendingNews.domain.Tweet;

import java.util.List;

public interface TwitterRepository extends PagingAndSortingRepository<Tweet, Long> {

    @Query("select fortune from Fortune fortune order by RAND()")
    public List<Tweet> randomFortunes(Pageable pageable);
}
