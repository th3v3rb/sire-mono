package com.dantesoft.sireregistryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class SireRegistryServiceApplication {

    public static void main(String[] args) {
        SpringApplication
                .run(SireRegistryServiceApplication.class, args);
    }

}
