/**
 * 
 */
package com.apress.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Siva
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
public class SpringbootRestAPISecurityDemoApplication 
{
	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestAPISecurityDemoApplication.class, args);
	}
}

