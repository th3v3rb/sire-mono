package com.dantesoft.sireproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SireProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SireProductServiceApplication.class, args);
    }

}
