package cn.spk.data.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping
public class RibbonController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("getServiceInstance")
    public ServiceInstance getServiceInstance(@RequestBody Map map) {
        String serviceId = (String) map.get("serviceId");
        ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
        System.out.println(serviceInstance.getHost());
        return serviceInstance;
    }
}
