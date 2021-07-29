package com.techstack.wallet_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableEurekaClient
@EnableWebSecurity
@SpringBootApplication
public class WalletServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WalletServiceApplication.class, args);
    }
    
}

