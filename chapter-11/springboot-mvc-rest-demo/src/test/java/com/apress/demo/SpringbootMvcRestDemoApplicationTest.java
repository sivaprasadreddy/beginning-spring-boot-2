/**
 *
 */
package com.apress.demo;

import com.apress.demo.entities.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Siva
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootMvcRestDemoApplicationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testGetAllPosts() {
        ResponseEntity<Post[]> responseEntity = restTemplate.getForEntity("/posts", Post[].class);
        List<Post> posts = Arrays.asList(responseEntity.getBody());
        assertNotNull(posts);
    }

    @Test
    public void testGetPostById() {
        Post post = restTemplate.getForObject("/posts/1", Post.class);
        assertNotNull(post);
    }

    @Test
    public void testCreatePost() {
        Post post = new Post();
        post.setTitle("Exploring SpringBoot REST");
        post.setContent("SpringBoot is awesome!!");
        post.setCreatedOn(new Date());

        ResponseEntity<Post> postResponse = restTemplate.postForEntity("/posts", post, Post.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdatePost() {
        int id = 1;
        Post post = restTemplate.getForObject("/posts/" + id, Post.class);
        post.setContent("This my updated post1 content");
        post.setUpdatedOn(new Date());

        restTemplate.put("/posts/" + id, post);

        Post updatedPost = restTemplate.getForObject("/posts/" + id, Post.class);
        assertNotNull(updatedPost);
    }

    @Test
    public void testDeletePost() {
        int id = 2;
        Post post = restTemplate.getForObject("/posts/" + id, Post.class);
        assertNotNull(post);

        restTemplate.delete("/posts/" + id);

        try {
            post = restTemplate.getForObject("/posts/" + id, Post.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
