package com.polyfrontiere.api.source;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class APIConfig {
    @Bean
    public RestTemplate getRestTemplateForAPI(){
        return new RestTemplate();
    }

}
