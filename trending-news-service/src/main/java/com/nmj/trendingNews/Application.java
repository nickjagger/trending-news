package com.nmj.trendingNews;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCircuitBreaker
//@EnableJpaRepositories
//@EnableDiscoveryClient
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//	@Bean
	//	public Filter hystrixRequestContextServletFilter() {
	//		return new HystrixRequestContextServletFilter();
	//	}
}
