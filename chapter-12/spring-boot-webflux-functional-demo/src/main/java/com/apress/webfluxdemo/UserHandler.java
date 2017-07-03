package com.apress.webfluxdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;


@Component
public class UserHandler {
    private UserReactiveRepository userReactiveRepository;

    @Autowired
    public UserHandler(UserReactiveRepository userReactiveRepository) {
        this.userReactiveRepository = userReactiveRepository;
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest request)
    {
        Flux<User> allUsers = userReactiveRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(allUsers, User.class);
    }

    public Mono<ServerResponse> getUserById(ServerRequest request)
    {
        Mono<User> userMono = userReactiveRepository.findById(request.pathVariable("id"));
        Mono<ServerResponse> notFount = ServerResponse.notFound().build();

        return userMono.flatMap(user -> ServerResponse.ok()
                                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                                            .body(fromObject(user)))
                        .switchIfEmpty(notFount);
    }

    public Mono<ServerResponse> saveUser(ServerRequest request)
    {
        Mono<User> userMono = request.bodyToMono(User.class);
        Mono<User> mono = userMono.flatMap(user -> userReactiveRepository.save(user));
        return ServerResponse.ok().body(mono, User.class);
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request)
    {
       String id = request.pathVariable("id");
        return ServerResponse.ok().build(userReactiveRepository.deleteById(id));
    }
}
