package com.cornucopia.cornucopiagateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@SpringCloudApplication
public class CornucopiaGatewayApplication {
	public static void main(String[] args) {
		SpringApplication.run(CornucopiaGatewayApplication.class, args);
	}
}
