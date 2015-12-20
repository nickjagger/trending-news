package com.nmj.trendingNews.controllers;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.nmj.trendingNews.Application;
import com.nmj.trendingNews.domain.GithubUser;
import com.nmj.trendingNews.service.GitHubService;


/**
 * Based on Spring async controller test.
 * 
 * @see https://github.com/spring-projects/spring-mvc-showcase/blob/master/src/test/java/org/springframework/samples/mvc/async/DeferredResultControllerTests.java
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
//@IntegrationTest({ "server.port:0", "spring.config.name:configserver" })
public class TrendingNewsControllerTest {

	@Mock
    private GitHubService githubServiceMock;
	
	@InjectMocks
	private TrendingNewsController classUnderTest;
	
	private MockMvc mockMvc;
		
    @Before
    public void setup() {
    	MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(classUnderTest).build();
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
	
    @Test
    public void getUserSuccess() throws Exception {
    	final String testUserName = "testUser";
    	GithubUser user = new GithubUser();
    	user.setName(testUserName);
    
    	when(githubServiceMock.getUser(testUserName)).thenReturn(user);

    	
    	MvcResult mvcResult = this.mockMvc.perform(get("/github/" + testUserName))
    			.andExpect(status().isOk())
    			.andExpect(request().asyncStarted())
    			.andExpect(request().asyncResult(instanceOf(GithubUser.class)))
    			.andReturn();
    	
    	GithubUser githubUser = (GithubUser) mvcResult.getAsyncResult();
    	assertEquals(testUserName, githubUser.getName());
    	
        verify(githubServiceMock, times(1)).getUser(testUserName);
        verifyNoMoreInteractions(githubServiceMock);
    }
}
