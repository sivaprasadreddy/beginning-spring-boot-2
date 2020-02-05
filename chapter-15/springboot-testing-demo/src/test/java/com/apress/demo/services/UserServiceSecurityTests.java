package com.apress.demo.services;


import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

import com.apress.demo.entities.User;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Siva
 *
 */
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class UserServiceSecurityTests
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationContext context;

	@Autowired
	AuthenticationManager authenticationManager;

	private Authentication authentication;
	
	@BeforeEach
	public void init() {
		//AuthenticationManager authenticationManager = this.context.getBean(AuthenticationManager.class);
		this.authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken("admin", "admin123"));
	}

	@AfterEach
	public void close() {
		SecurityContextHolder.clearContext();
	}
	
	@Test
	public void testGetUserById() {
		Optional<User> user = userService.findUserById(1);
		assertTrue(user.isPresent());
	}
	
	@Test
	public void deleteUserUnauthenticated() {
		assertThrows(AuthenticationCredentialsNotFoundException.class, () -> {
			userService.deleteUser(3);
		});
	}
	
	@Test
	public void deleteUserAuthenticated() {
		SecurityContextHolder.getContext().setAuthentication(this.authentication);
		userService.deleteUser(3);
	}
	
	@Test
	@WithMockUser//(username="admin")
	public void createUserWithMockUser() {
		User user = new User();
		user.setName("Yosin");
		user.setEmail("yosin@gmail.com");
		user.setPassword("yosin123");
		
		userService.createUser(user);
	}
	
	/*
	@Test
	@WithUserDetails
	public void createUserWithUserDetails()
	{
		User user = new User();
		user.setName("Yosin");
		user.setEmail("yosin@gmail.com");
		user.setPassword("yosin123");
		
		userService.createUser(user);
	}
	*/
	
	@Test
	@WithMockUser
	public void updateUserWithMockUser() {
		User user = userService.findUserById(1).get();
		user.setName("Yo");
		userService.updateUser(user);
	}
	
	@Test
	@WithMockUser(username="admin",roles={"USER","ADMIN"})
	public void deleteUserAuthenticatedWithMockUser() {
		userService.deleteUser(2);
	}

}
