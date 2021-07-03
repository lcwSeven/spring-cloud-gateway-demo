package com.spring.gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liucaiwen
 * @date 2021/7/3
 */
@RestController
public class HelloController {
    @RequestMapping("/product/{id}")
    public String queryProduct(@PathVariable("id")String id){
        return "获取商品:"+id;
    }
}
