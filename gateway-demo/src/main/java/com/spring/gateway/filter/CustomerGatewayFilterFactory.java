package com.spring.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * 自定义网关过滤器
 * @author liucaiwen
 * @date 2021/7/3
 */
//@Component
public class CustomerGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

                MultiValueMap<String, String> queryParams = exchange.getRequest().getQueryParams();
                //判断请求参数中是否包含配置的参数
                if (queryParams.containsKey(config.getName())) {
                    String value = queryParams.getFirst(config.getName());
                    //判断配置值和参数值是否相等 相等则让请求通过 否则拒绝
                    if (config.getValue().equals(value)) {
                        return chain.filter(exchange);
                    }
                }
                ServerHttpResponse response = exchange.getResponse();
                response.getHeaders().add("Content-Type", "application/json");
                response.setStatusCode(HttpStatus.OK);
                String message = "{\"message\":\" 参数错误\"}";
                DataBuffer buffer = response.bufferFactory().wrap(message.getBytes(StandardCharsets.UTF_8));
                return response.writeWith(Mono.just(buffer));
            }
        };
    }
}
