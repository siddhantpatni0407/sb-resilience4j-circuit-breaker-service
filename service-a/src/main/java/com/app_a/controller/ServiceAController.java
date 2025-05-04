package com.app_a.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Controller for Service A that calls Service B.
 * Uses Resilience4j CircuitBreaker for fault tolerance.
 */
@Slf4j
@RestController
@RequestMapping("/a")
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "http://localhost:8081/";
    private static final String SERVICE_A = "serviceA";

    private int count = 1;

    /**
     * Endpoint that calls Service B with CircuitBreaker protection.
     * Logs retry attempts and timestamp.
     */
    @GetMapping
    @CircuitBreaker(name = SERVICE_A, fallbackMethod = "serviceAFallback")
    public String serviceA() {
        String url = BASE_URL + "b";

        log.info("Calling Service B (attempt #{}) at {}", count++, new Date());

        String response = restTemplate.getForObject(url, String.class);

        log.info("Received response from Service B: {}", response);

        return response;
    }

    /**
     * Fallback method for CircuitBreaker in case Service B is down or slow.
     * Logs the fallback reason.
     */
    public String serviceAFallback(Exception e) {
        log.error("Fallback triggered in Service A due to exception: {}", e.toString());
        return "This is a fallback method for Service A";
    }

}