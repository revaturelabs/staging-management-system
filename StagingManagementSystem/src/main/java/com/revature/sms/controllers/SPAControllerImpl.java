package com.revature.sms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class SPAControllerImpl {

    @RequestMapping(method = RequestMethod.GET)
    public String getSPA() {

        return "index.html";
    }
}
