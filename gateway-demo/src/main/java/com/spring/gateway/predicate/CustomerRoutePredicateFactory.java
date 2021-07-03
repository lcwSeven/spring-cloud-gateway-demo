package com.spring.gateway.predicate;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author liucaiwen
 * @date 2021/7/3
 */
@Component
public class CustomerRoutePredicateFactory extends AbstractRoutePredicateFactory<CustomerRoutePredicateFactory.Config> {

    public CustomerRoutePredicateFactory() {
        super(CustomerRoutePredicateFactory.Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("name", "value");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange exchange) {
                //判断请求参数中是否有配置的请求参数 如果没有则不让请求通过
                if (!exchange.getRequest().getQueryParams().containsKey(config.name)) {
                    return false;
                }
                //从请求参数中取出参数值 判断是否和配置的值相等 相等 则让请求通过
                String value = exchange.getRequest().getQueryParams().getFirst(config.name);
                if (value.equals(config.value)) {
                    return true;
                }
                return false;
            }
        };
    }

    public static class Config {

        private String name;

        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
