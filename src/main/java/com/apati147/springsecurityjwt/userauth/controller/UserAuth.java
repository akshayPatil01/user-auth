package com.apati147.springsecurityjwt.userauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuth {

    @RequestMapping("/welcome")
    public String welcome() {
        return "Hello World!";
    }
}
