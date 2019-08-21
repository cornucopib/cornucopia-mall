package com.cornucopia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.cornucopia.service.item.mapper")
public class CornucopiaItemService {

    public static void main(String[] args) {
        SpringApplication.run(CornucopiaItemService.class, args);
    }

}
