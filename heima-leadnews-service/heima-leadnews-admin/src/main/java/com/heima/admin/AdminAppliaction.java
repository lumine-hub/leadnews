package com.heima.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AdminAppliaction {
    public static void main(String[] args) {
        SpringApplication.run(AdminAppliaction.class, args);
    }
}
