package com.bistu.equip.equipment.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 18:17
 */
@Configuration
@MapperScan("com.bistu.equip.equipment.mapper")
@ComponentScan("com.bistu.equip")
public class EquipmentConfiguration {
	
	// 配置分页插件
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
	
	
	/**
	 * 乐观锁插件
	 * @return
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}
	
}
