package com.dantesoft.siregatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SireGatewayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SireGatewayServiceApplication.class, args);
    }

}
