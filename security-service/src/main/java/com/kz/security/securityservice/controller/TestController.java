package com.kz.security.securityservice.controller;

import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 测试
 * @Author KatieZ
 * @Date Created in 14:20  14:20
 */
@RestController
public class TestController {

    @GetMapping("/hello")
    public String hello() {
        return"hello";
    }

}
