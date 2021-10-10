package com.bistu.equip.gateway.config;




import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;
import sun.rmi.runtime.Log;


/**
 * 处理跨域的配置类
 * @author Dx666
 * @Description
 * @Date 2021/5/21 - 14:46
 */
@Slf4j
@Configuration
public class CorsConfig {
	
	/**
	 * 创建一个处理跨域问题的类
	 * @return
	 */
	@Bean
	public CorsWebFilter corsFilter() {
		log.info("跨域请求处理.....");
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedMethod("*");
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
		source.registerCorsConfiguration("/**", config);
		return new CorsWebFilter(source);
	}
}
