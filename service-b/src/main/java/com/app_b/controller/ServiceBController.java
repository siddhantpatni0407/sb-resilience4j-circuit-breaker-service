package com.app_b.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for Service B which is called by Service A.
 */
@Slf4j
@RestController
@RequestMapping("/b")
public class ServiceBController {

    /**
     * Simple endpoint that returns a success message.
     */
    @GetMapping
    public String serviceB() {
        log.info("Service B endpoint was called by Service A");
        return "Service B is called from Service A";
    }

}