package com.app_a.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author Siddhant Patni
 */
@Configuration
@Slf4j
public class AppConfig {

    @Bean
    public RestTemplate restTemplate() {
        log.info("Initializing RestTemplate bean...");
        return new RestTemplate();
    }

}