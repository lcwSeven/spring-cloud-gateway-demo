package com.spring.gateway.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liucaiwen
 * @date 2021/7/3
 */
@RestController
public class FlowRuleController {

    @RequestMapping("/hello")
    public String flowRuleTest() {
        try (Entry entry = SphU.entry("FlowRuleTest")) {
            return "hello sentinel";
        } catch (BlockException e) {
            return "系统繁忙，请稍后再试！";
        }
    }


    /**
     * 基于注解方式的限流配置
     *
     * @param id id
     * @return String
     */
    @RequestMapping("/order/{id}")
    @SentinelResource(value = "FlowRuleTest", blockHandler = "queryOrderBlockHandler")
    public String queryOrder(@PathVariable("id") String id) {
        if ("0".equals(id)) {
            throw new RuntimeException();
        }
        return "获取到订单id=" + id;
    }

    /**
     * 注意此方法参数类型要和 原方法参数一致，并且最后还要加一个类型为BlockException 的参数 否则会报错
     *
     * @param id id
     * @param e  BlockException
     * @return String
     */
    public String queryOrderBlockHandler(String id, BlockException e) {
        return "查询订单被限流ID=" + id;

    }
}
