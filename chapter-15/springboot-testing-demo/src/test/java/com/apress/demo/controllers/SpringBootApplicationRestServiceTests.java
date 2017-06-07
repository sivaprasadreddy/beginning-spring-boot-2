package com.apress.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.apress.demo.SpringbootTestingDemoApplication;
import com.apress.demo.entities.User;


/**
 * @author Siva
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootTestingDemoApplication.class,
				webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBootApplicationRestServiceTests
{

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testPing()
	{
		String resp = restTemplate.withBasicAuth("admin","admin123")
				.getForObject("/ping", String.class);
		assertTrue(resp.contains("Up & Running"));
	}
	
	@Test //@Ignore
	public void testGetUsers()
	{
		ResponseEntity<String> responseEntity = restTemplate.withBasicAuth("admin","admin123")
														.getForEntity("/users", String.class);
		System.err.println(responseEntity);
        
        ResponseEntity<PagedResources<User>> responseEntity1 =
		restTemplate.withBasicAuth("admin","admin123")
				.exchange("/users",
				        HttpMethod.GET, 
				        null, 
				        new ParameterizedTypeReference<PagedResources<User>>() {}
				        );
		assertTrue(responseEntity.getStatusCode() == HttpStatus.OK);
		PagedResources<User> userResource = responseEntity1.getBody();
		Collection<User> users = userResource.getContent();
		assertNotNull(users);
		assertEquals(3, users.size());
	}

}
