package com.es.phoneshop.web.controller.pages.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {

    @GetMapping(value = "/{id}")
    public String getOrder(@PathVariable Long id) {
        return "orderOverview";
    }
}
