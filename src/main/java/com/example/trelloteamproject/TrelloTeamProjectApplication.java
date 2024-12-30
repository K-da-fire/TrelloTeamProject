package com.example.trelloteamproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class TrelloTeamProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrelloTeamProjectApplication.class, args);
    }

}
