package com.spring.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author liucaiwen
 * @date 2021/7/3
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GateDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(GateDemoApplication.class,args);
    }
}
