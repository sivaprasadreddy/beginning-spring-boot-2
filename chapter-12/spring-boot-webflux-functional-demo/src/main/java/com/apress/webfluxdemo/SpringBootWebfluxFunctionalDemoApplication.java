package com.apress.webfluxdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@SpringBootApplication
public class SpringBootWebfluxFunctionalDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxFunctionalDemoApplication.class, args);
	}

	@Autowired
	UserHandlerFunctions userHandlerFunctions;

	@Bean
	public RouterFunction<ServerResponse> monoRouterFunction() {
		return
				nest(path("/api/users"),
						nest(accept(APPLICATION_JSON),
								route(GET("/{id}"), userHandlerFunctions::getUserById)
								.andRoute(method(HttpMethod.GET), userHandlerFunctions::getAllUsers)
								.andRoute(DELETE("/{id}"), userHandlerFunctions::deleteUser)
								.andRoute(POST("/"), userHandlerFunctions::saveUser)));
	}

}
