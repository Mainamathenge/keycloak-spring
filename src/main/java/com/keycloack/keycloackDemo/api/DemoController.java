package com.keycloack.keycloackDemo.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/roles")
public class DemoController {

    @GetMapping
    public String hello() {
        return "Hello from Spring boot & Keycloak";
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('admin')")
    public String hello2() {
        return "Hello from Spring boot & Keycloak - ADMIN";
    }
}
