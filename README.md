# trending-news

## overview
Project showcasing Spring Boot and Spring Cloud Netflix utilisation for creating a microservice. The service returns the 5 latest Guardian (http://www.theguardian.com) news articles with corresponding tweets retrieved from the Twitter API (https://twitter.com). 

The project comprises the following features:

* Asynchronous HTTP request processing leveraging Servlet 3.0 specification to avoid blocking request threads and thread pool starvation.
* Externalised configuration, with a view towards cloud deployment, provided by Spring Cloud Config [Spring Cloud Config](http://cloud.spring.io/spring-cloud-config).
* Circuit breaker using Hystrix to provide fault tolerance and prevent cascading failure [Hystrix](http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html#_circuit_breaker_hystrix_clients).
* Client side load balancing using Ribbon [Ribbon](http://cloud.spring.io/spring-cloud-netflix/spring-cloud-netflix.html#spring-cloud-ribbon).


## requirements
* Java 8
* Maven 3
* Github account and repository
 
## usage
1. mvn install 
2. Replace the Guardian key and Twitter key and secret with your own versions in file _trending-news.yml_ Twitter key and secret can be created here [Twitter App Management](https://apps.twitter.com). Guardian key can be created here [Guardian Open Platform](http://bonobo.capi.gutools.co.uk/register/developer).
4. Add the modified _trending-news.yml_ file to your Github repo and point the trending-news-config-server to this new location by modifying file _/trending-news-config-server/src/main/resources/application.yml_
5. In a terminal, navigate to the trending-news-config-server root folder and enter _mvn spring-boot:run_
6. In a terminal, navigate to the trending-news-service root folder and enter _mvn spring-boot:run_ Once started, the application should be available on http://localhost:8080/trending-news/news
7. In a terminal, navigate to the trending-news-hystrix-dashboard and enter _mvn spring-boot:run_. Once started, navigate to http://localhost:7979/. Enter the url http://localhost:8080/trending-news/hystrix.stream in the first text field and click _Monitor Stream_. The hystrix dashboard will show the status of currently executing requests and the circuit breaker statuses.
8. Spring Boot Actuator metrics are available on the endpoint http://localhost:8080/trending-news/metrics.
