package com.cornucopia.cornucopiaeurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class CornucopiaEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornucopiaEurekaServerApplication.class, args);
    }

}
