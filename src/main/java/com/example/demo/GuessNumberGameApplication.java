package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GameProperties.class)
public class GuessNumberGameApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuessNumberGameApplication.class, args);
    }
}
