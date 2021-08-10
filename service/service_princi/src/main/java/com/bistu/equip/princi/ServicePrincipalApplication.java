package com.bistu.equip.princi;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 17:1
 */
@ComponentScan("com.bistu")
@SpringBootApplication
@EnableSwagger2
@CrossOrigin // 设置跨域访问
public class ServicePrincipalApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServicePrincipalApplication.class, args);
	}
}
