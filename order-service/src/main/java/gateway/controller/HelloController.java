package gateway.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liucaiwen
 * @date 2021/7/3
 */
@RestController
public class HelloController {
    @RequestMapping("/order/{id}")
    public String queryOrder(@PathVariable("id") String id) {
        System.out.println("查询订单id="+id);
        return "获取订单:" + id;
    }
}
