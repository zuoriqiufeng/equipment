package com.bistu.equip.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 17:38
 */
// 不加载数据库配置
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class ServiceGatewayApplication {
	public static void main(String args[]) {
		SpringApplication.run(ServiceGatewayApplication.class, args);
	}
}
