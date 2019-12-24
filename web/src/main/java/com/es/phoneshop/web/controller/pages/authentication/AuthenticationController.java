package com.es.phoneshop.web.controller.pages.authentication;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/authentication")
public class AuthenticationController {

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {

        return "login";
    }
}
