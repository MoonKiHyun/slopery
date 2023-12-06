package com.moon.slopery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SloperyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SloperyApplication.class, args);
    }

}
