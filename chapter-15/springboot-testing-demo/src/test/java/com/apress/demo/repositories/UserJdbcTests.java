/**
 * 
 */
package com.apress.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Siva
 *
 */
@RunWith(SpringRunner.class)
@JdbcTest
public class UserJdbcTests {

	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Before
	public void init()
	{
		jdbcTemplate.execute("create table people(id int, name varchar(100))");
		jdbcTemplate.execute("insert into people(id, name) values(1, 'John')");
		jdbcTemplate.execute("insert into people(id, name) values(2, 'Remo')");
		jdbcTemplate.execute("insert into people(id, name) values(3, 'Dale')");
	}
	
    @Test
    public void testFindUserCount() throws Exception {
        Integer count = this.jdbcTemplate.queryForObject("select count(*) from people", Integer.class);
        assertThat(count).isEqualTo(3);
    }
    
}
