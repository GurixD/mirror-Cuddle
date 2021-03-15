package com.hearc.cuddle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan("com.hearc.entities")
//@EnableJpaRepositories("com.hearc")
public class CuddleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuddleApplication.class, args);
    }
}
