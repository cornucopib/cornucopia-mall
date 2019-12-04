package com.cornucopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cornucopia
 * @version 1.0
 * @since 2019-09-10
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CornucopiaUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(CornucopiaUploadApplication.class);
    }

}
