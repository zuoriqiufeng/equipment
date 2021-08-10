package com.bistu.equip.equipment.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 18:17
 */
@Configuration
public class EquipmentConfiguration {
	
	// 配置分页插件
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
