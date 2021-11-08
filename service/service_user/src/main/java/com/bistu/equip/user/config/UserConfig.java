package com.bistu.equip.user.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 14:28
 */
@Configuration
@ComponentScan("com.bistu.equip")
public class UserConfig {
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
