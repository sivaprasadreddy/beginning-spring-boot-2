/**
 * 
 */
package com.apress.demo.controllers;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.apress.demo.entities.Todo;
import com.apress.demo.repositories.TodoRepository;


/**
 * @author Siva
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers= TodoRestController.class, secure=false)
//@ContextConfiguration(classes={SpringbootTestingDemoApplication.class, WebSecurityConfig.class})
public class TodoRestControllerTests {

	@Autowired
    private MockMvc mvc;

    @MockBean
    private TodoRepository todoRepository;

    @Test
    public void testFindTodoById() throws Exception {
    	Todo todo = new Todo();
    	todo.setId(1);
    	todo.setText("Todo1");
    	todo.setDone(false);
        given(this.todoRepository.findById(1)).willReturn(Optional.of(todo));
        this.mvc.perform(get("/api/todos/1")
        		//.with(user("admin").password("admin123").roles("USER","ADMIN"))
        		.accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.text", is("Todo1")))
                .andExpect(jsonPath("$.done", is(false)));
 
        verify(todoRepository, times(1)).findById(1);
    }
    
}
