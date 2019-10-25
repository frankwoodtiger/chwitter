package com.chi.twitter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/*
 The default setting scans the existing package that this application class resides.
 In this case, it is com.chi.twitter as the base package for component scanning.

 To run a Spring Boot project, go to project root and run:
 mvn clean spring-boot:run
 */
public class TwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterApplication.class, args);
    }

}
