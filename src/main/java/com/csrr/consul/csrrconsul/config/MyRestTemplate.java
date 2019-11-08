package com.csrr.consul.csrrconsul.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyRestTemplate {

    @Bean
    public RestTemplate initMyRestTemplate() {
        return new RestTemplate();
    }
}