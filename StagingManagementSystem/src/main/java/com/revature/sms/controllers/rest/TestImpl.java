package com.revature.sms.controllers.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestImpl {

    @GetMapping("/testone")
    public void permissionTest() {

    }
}
