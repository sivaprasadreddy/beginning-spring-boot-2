package com.apress.webfluxdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class SpringBootWebfluxFunctionalDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxFunctionalDemoApplication.class, args);
	}

	@Autowired
	UserHandler userHandler;

	@Bean
	public RouterFunction<ServerResponse> routerFunctions() {
		return
				nest(path("/api/users"),
						nest(accept(APPLICATION_JSON),
								route(GET("/{id}"), userHandler::getUserById)
								.andRoute(method(HttpMethod.GET), userHandler::getAllUsers)
								.andRoute(DELETE("/{id}"), userHandler::deleteUser)
								.andRoute(POST("/"), userHandler::saveUser)));
	}

	@Bean
	public RouterFunction<ServerResponse> echoRouterFunction() {
		return route(GET("/echo"), request -> ok().body(fromObject(request.queryParam("name"))));
	}

}

@RestController
class HomeController
{
	@GetMapping("/home")
	public Mono<String> home()
	{
		return Mono.just("home");
	}
}
