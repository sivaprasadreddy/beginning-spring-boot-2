package com.apress.demo.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.apress.demo.exceptions.UserImportServiceCommunicationFailure;
import com.apress.demo.model.UsersImportResponse;

/**
 * @author Siva
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UsersImportServiceMockitoTest
{

    @Mock
    private UsersImporter usersImporter;

    @InjectMocks
    private UsersImportService usersImportService;
    
    @Test
    public void testUsersImport()
	{
    	UsersImportResponse response = usersImportService.importUsers();
    	System.err.println(response);
    	assertEquals(0, response.getRetryCount());
    	assertEquals("SUCCESS", response.getStatus());
    	
    	when(usersImporter.importUsers()).thenThrow(new UserImportServiceCommunicationFailure());
    	response = usersImportService.importUsers();
    	System.err.println(response);
    	assertEquals(3, response.getRetryCount());
    	assertEquals("FAILURE", response.getStatus());
	}
	
}
