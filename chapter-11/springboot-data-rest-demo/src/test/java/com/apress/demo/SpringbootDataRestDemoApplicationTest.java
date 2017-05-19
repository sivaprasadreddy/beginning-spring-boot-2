/**
 * 
 */
package com.apress.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.Collections;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.apress.demo.entities.User;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Siva
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootDataRestDemoApplication.class,
webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringbootDataRestDemoApplicationTest
{

	@Ignore
	@Test
	public void testGetUsers()
	{

		RestTemplate restTemplate = restTemplate();
		
		ResponseEntity<PagedResources<User>> responseEntity = 
				restTemplate.exchange(
								"http://localhost:8080/api/users",
						        HttpMethod.GET, 
						        null, 
						        new ParameterizedTypeReference<PagedResources<User>>()
						        {}, 
						        Collections.emptyMap()
						        );
		
		if (responseEntity.getStatusCode() == HttpStatus.OK)
		{
			PagedResources<User> userResource = responseEntity.getBody();
			Collection<User> users = userResource.getContent();
			System.err.println(users);
			assertNotNull(users);
			assertEquals(3, users.size());
		}
	}
	
	protected RestTemplate restTemplate() 
	{
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 objectMapper.registerModule(new Jackson2HalModule());

		  MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		  converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		  converter.setObjectMapper(objectMapper);
		  return new RestTemplate(Collections.<HttpMessageConverter<?>> singletonList(converter));
	}
}
