package com.neo.kttvapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class KttvApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(KttvApiApplication.class, args);
    }

}
