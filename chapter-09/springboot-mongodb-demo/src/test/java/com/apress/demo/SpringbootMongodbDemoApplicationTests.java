package com.apress.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Siva
 *
 */

//@SpringBootTest
@DataMongoTest
public class SpringbootMongodbDemoApplicationTests
{

	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findAllUsers()  {
		List<User> users = userRepository.findAll();
		assertNotNull(users);
		assertTrue(!users.isEmpty());
	}
	
	@Test
	public void findUserById()  {
		User user = userRepository.findById("1").get();
		assertNotNull(user);
	}
	
	@Test
	public void createUser() {
		User user = new User("3", "Joseph", "joseph@gmail.com");
        User savedUser = userRepository.save(user);
        User newUser = userRepository.findById(savedUser.getId()).get();
        assertEquals("Joseph", newUser.getName());
        assertEquals("joseph@gmail.com", newUser.getEmail());
        
    }
	
	@Test
	public void findUserByName()  {
		User user = userRepository.findByUserName("Robert");
		assertNotNull(user);
	}
}
