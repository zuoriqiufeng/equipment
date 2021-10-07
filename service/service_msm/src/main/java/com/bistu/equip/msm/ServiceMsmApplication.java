package com.bistu.equip.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author Dx666
 * @Description
 * @Date 2021/9/21 - 14:29
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ServiceMsmApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceMsmApplication.class, args);
		
	}
}
