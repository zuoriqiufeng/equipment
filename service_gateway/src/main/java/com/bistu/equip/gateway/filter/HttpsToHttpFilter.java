package com.bistu.equip.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author Dx666
 * @Description
 * @Date 2021/10/18 - 21:19
 */
//@Slf4j
//@Component
//public class HttpsToHttpFilter implements GlobalFilter, Ordered {
//
//	private static final int HTTPS_TO_HTTP_FILTER_ORDER = 10098;
//	/**
//	 * 过滤方法
//	 * @param exchange
//	 * @param chain
//	 * @return
//	 */
//	@Override
//	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//		URI originalUri = exchange.getRequest().getURI();
//		ServerHttpRequest request = exchange.getRequest();
//		ServerHttpRequest.Builder mutate = request.mutate();
//		String forwardedUri = request.getURI().toString();
//		if(forwardedUri != null && forwardedUri.startsWith("https")) {
//			try {
//				URI mutateUri = new URI("http",
//						originalUri.getUserInfo(),
//						originalUri.getHost(),
//						originalUri.getPort(),
//						originalUri.getPath(),
//						originalUri.getQuery(),
//						originalUri.getFragment());
//				mutate.uri(mutateUri);
//			} catch (Exception e) {
//				throw new IllegalStateException(e.getMessage(), e);
//			}
//		}
//		ServerHttpRequest build = mutate.build();
//		return chain.filter(exchange.mutate().request(build).build());
//	}
//
//	@Override
//	public int getOrder() {
//		return HTTPS_TO_HTTP_FILTER_ORDER;
//	}
//}
