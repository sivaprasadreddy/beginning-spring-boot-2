package com.apress.demo;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.apress.demo.UserImportServiceCommunicationFailure;
import com.apress.demo.UserService;
import com.apress.demo.UsersImportResponse;
import com.apress.demo.UsersImporter;

/**
 * @author Siva
 *
 */
public class SpringMockitoTests
{
	@InjectMocks
    private UserService userService;

    @Mock
    private UsersImporter usersImporter;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testUsersImport()
	{
    	UsersImportResponse response = userService.importUsers();
    	System.err.println(response);
    	assertEquals(0, response.getRetryCount());
    	assertEquals("SUCCESS", response.getStatus());
    	
    	when(usersImporter.importUsers()).thenThrow(new UserImportServiceCommunicationFailure());
    	response = userService.importUsers();
    	System.err.println(response);
    	assertEquals(3, response.getRetryCount());
    	assertEquals("FAILURE", response.getStatus());
	}
	
}
