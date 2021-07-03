package com.spring.gateway.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liucaiwen
 * @date 2021/7/3
 */
@RestController
public class NacosDataSourceController {
    @GetMapping("/test-nacos")
    @SentinelResource(value = "test-nacos-datasource", fallback = "nacosFallBack")
    public String testNacos() {
        return "hello test nacos";
    }

    public String nacosFallBack() {
        return "nacosFallBack";
    }


}
