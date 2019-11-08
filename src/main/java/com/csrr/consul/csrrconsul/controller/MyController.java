package com.csrr.consul.csrrconsul.controller;


import com.alibaba.fastjson.JSONObject;

import com.csrr.consul.csrrconsul.config.MyRestTemplate;
import com.csrr.consul.csrrconsul.dto.CompanyDTO;
import com.csrr.consul.csrrconsul.dto.RequestWithPageDTO;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MyController {



    @Autowired
    private LoadBalancerClient loadBalancer;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    MyRestTemplate myRestTemplate;
    /**
     * 获取所有服务
     */
    @GetMapping("/services")
    public Object services() {
        List<ServiceInstance> instances = discoveryClient.getInstances("csrr-consul");
        return discoveryClient.getInstances("csrr-consul");

    }

    /**
     * 从所有服务中选择一个服务（轮询）
     */
    @GetMapping("/discover")
    public JSONObject discover(
            @RequestParam("startPage") Integer startPage,
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("name") String name
    ) {
        ServiceInstance choose = loadBalancer.choose("csrr-consul");
        System.out.println(       choose.getUri().toString());

        CompanyDTO  companyDTO = new CompanyDTO();

        RequestWithPageDTO<CompanyDTO> dto = new RequestWithPageDTO();
        companyDTO.setCompanyName(name);
        dto.setStartPage(startPage);
        dto.setPageSize(pageSize);
        dto.setData(companyDTO);
        JSONObject callServiceResult = myRestTemplate.initMyRestTemplate().postForObject( choose.getUri().toString() + "/csrr/searchWithPage.do",dto, JSONObject.class);
        System.out.println(JSONObject.toJSONString(callServiceResult));




        return callServiceResult;
    }



    @GetMapping("/discover1")
    public Object discover1(

    ) {

        return loadBalancer.choose("csrr-consul").getUri().toString();
    }

}
