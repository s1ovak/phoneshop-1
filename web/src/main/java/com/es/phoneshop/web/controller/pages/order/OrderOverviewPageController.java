package com.es.phoneshop.web.controller.pages.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {

    @GetMapping
    public String getOrder() {
        return "orderOverview";
    }
}
