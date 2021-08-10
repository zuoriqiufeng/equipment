package com.bistu.equip.equipment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 20:56
 */
@ComponentScan("com.bistu")
@SpringBootApplication
@EnableSwagger2
@CrossOrigin // 允许跨域访问
public class ServiceEquipApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServiceEquipApplication.class, args);
	}
}
