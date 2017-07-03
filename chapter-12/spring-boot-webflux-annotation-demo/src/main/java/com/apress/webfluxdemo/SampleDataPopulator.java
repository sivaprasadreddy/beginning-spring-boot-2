package com.apress.webfluxdemo;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class SampleDataPopulator implements CommandLineRunner
{
    @Value("${sample.users.count}")
    private int sampleUserCount;

    @Autowired
    private UserReactiveRepository userReactiveRepository;

    @Override
    public void run(String... strings) throws Exception {
        Faker faker = new Faker(new Locale("EN"));
        userReactiveRepository.deleteAll().subscribe();
        List<User> users = new ArrayList<>();
        for (int i=1; i<=sampleUserCount; i++)
        {
            String name = faker.name().firstName();
            users.add(new User(UUID.randomUUID().toString(), name, name + "@gmail.com"));
        }
        userReactiveRepository.saveAll(users)
                .doOnComplete(() -> System.out.println("Count:"+userReactiveRepository.count().block()))
                .subscribe();

    }
}
