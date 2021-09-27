package com.js.qa.qatools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QatoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(QatoolsApplication.class, args);
    }

}
