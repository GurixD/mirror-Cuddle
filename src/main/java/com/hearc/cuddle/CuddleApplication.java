package com.hearc.cuddle;

import com.hearc.cuddle.auth.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class CuddleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CuddleApplication.class, args);
    }
}
