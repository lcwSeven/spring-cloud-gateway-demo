package com.spring.gateway.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liucaiwen
 * @date 2021/7/3
 */
@RestController
public class DegradeRuleController {

    @RequestMapping("/degrade")
    @SentinelResource(value = "DegradeRuleTest"
            , fallback = "degradeRuleFallback")
    public String degradeRuleTest() throws Exception {
        Thread.sleep(1000);
        return "hello sentinel";
    }


    public String degradeRuleFallback() {
        return "系统繁忙，稍后再试";
    }
}
