package com.hjc.demo.controller;

import com.hjc.demo.entity.UserBorrowDetail;
import com.hjc.demo.service.BorrowService;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hjc
 */
@RestController
public class BorrowController {
    @Resource
    BorrowService service;
    @Resource
    private LoadBalancerClient loadBalancerClient;

    //通过 DiscoveryClient API 获取服务列表
    @Resource
    private DiscoveryClient discoveryClient;

    @RequestMapping("/borrow/{uid}")
    public UserBorrowDetail findUserBorrows(@PathVariable("uid") Integer uid) {
        return service.getUserBorrowDetailByUid(uid);
    }

    @GetMapping("/test-load-balancer")
    public String testLoadBalancer() {
        //获取负载均衡的服务提供者实例信息
        ServiceInstance instance = loadBalancerClient.choose("user-service");
        return instance.getHost() + ":" + instance.getPort();
    }

    //获取到“服务发现 Client”，即可读取到注册中心的微服务列表
    @GetMapping("/discovery")
    public List<String> discovery() {
        // 获取注册中心所有服务名称
        List<String> services = discoveryClient.getServices();
        for (String serviceName :
                services) {
            // 获取指定微服务名称的所有微服务实例
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
            for (ServiceInstance instance :
                    instances) {
                Map<String, Object> map = new HashMap<>();
                map.put("serviceName",serviceName);
                map.put("serviceId",instance.getServiceId());
                map.put("host",instance.getHost());
                map.put("port",instance.getPort());
                map.put("uri",instance.getUri());
                System.out.println(map);
            }
        }
        return services;
    }
}
