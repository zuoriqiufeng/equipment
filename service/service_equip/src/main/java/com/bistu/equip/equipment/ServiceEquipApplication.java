package com.bistu.equip.equipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 20:56
 */
@ComponentScan("com.bistu.equip")
@SpringBootApplication

//@CrossOrigin // 允许跨域访问
@EnableDiscoveryClient // 注册nacos服务
public class ServiceEquipApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceEquipApplication.class, args);
	}
}
