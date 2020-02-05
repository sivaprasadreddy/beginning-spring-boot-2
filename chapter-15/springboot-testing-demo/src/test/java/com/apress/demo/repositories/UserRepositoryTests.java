/**
 * 
 */
package com.apress.demo.repositories;

import com.apress.demo.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Siva
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class UserRepositoryTests {

	@Autowired
    private UserRepository repository;

    @Test
    public void testFindUserByEmail() throws Exception {
        User user = this.repository.findByEmail("admin@gmail.com");
        assertThat(user.getEmail()).isEqualTo("admin@gmail.com");
        assertThat(user.getPassword()).isEqualTo("admin");
    }
    
}
