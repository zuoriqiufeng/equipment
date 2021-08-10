package com.bistu.equip.princi.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Dx666
 * @Description
 * @Date 2021/8/8 - 16:59
 */
@Configuration
public class PrincipalConfig {
	// 配置分页插件
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
