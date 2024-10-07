package com.dantesoft.sireconfigservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class SireConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication
                .run(SireConfigServiceApplication.class, args);
    }
}
