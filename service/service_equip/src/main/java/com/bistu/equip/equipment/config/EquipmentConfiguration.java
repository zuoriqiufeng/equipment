package com.bistu.equip.equipment.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import sun.rmi.runtime.Log;

/**
 * @author Dx666
 * @Description
 * @Date 2021/7/31 - 18:17
 */
@Slf4j
@Configuration
@MapperScan("com.bistu.equip.equipment.mapper")
@ComponentScan("com.bistu.equip")
public class EquipmentConfiguration {
	
	// 配置分页插件
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		log.info("分页插件加载.....");
		return new PaginationInterceptor();
	}
	
	
	/**
	 * 乐观锁插件
	 * @return
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		log.info("乐观锁插件加载.....");
		return new OptimisticLockerInterceptor();
	}
	
}
