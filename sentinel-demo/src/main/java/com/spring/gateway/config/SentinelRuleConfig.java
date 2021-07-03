package com.spring.gateway.config;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liucaiwen
 * @date 2021/7/3
 */
@Component
public class SentinelRuleConfig {

    /**
     * 加载sentinel规则
     */
    @PostConstruct
    public void loadSentinelRule() {
        loadFlowRule();
//        loadDegradeRule();
    }

    /**
     * 加载限流规则
     */
    private void loadFlowRule() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        //设置限流资源
        rule.setResource("FlowRuleTest");
        //设置限流策略 QPS 或者 并发线程数
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(2);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }


    private void loadDegradeRule(){
        List<DegradeRule> rules = new ArrayList<>();
        DegradeRule rule = new DegradeRule();
        //设置资源
        rule.setResource("DegradeRuleTest");
        //设置降级策略
        rule.setGrade(RuleConstant.DEGRADE_GRADE_RT);
        //设置平均响应时间为2ms
        rule.setCount(2);
        rule.setTimeWindow(2);
        rules.add(rule);

        DegradeRuleManager.loadRules(rules);
    }


}
