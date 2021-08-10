package com.bistu.equip.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 20:56
 */
@EnableSwagger2 // 配置是应用swagger
@SpringBootApplication
@ComponentScan("com.bistu")
@CrossOrigin // 允许跨域访问
public class ServiceUserApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ServiceUserApplication.class, args);
	}
}
