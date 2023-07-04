package com.alive_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AliveBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliveBackendApplication.class, args);
    }

}
