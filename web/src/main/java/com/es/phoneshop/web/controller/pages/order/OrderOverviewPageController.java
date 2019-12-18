package com.es.phoneshop.web.controller.pages.order;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderService;
import com.es.phoneshop.web.controller.pages.exceptions.OrderNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping(value = "/orderOverview")
public class OrderOverviewPageController {

    @Resource
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public String getOrder(@PathVariable Long id, Model model) {
        Order order = orderService.getOrder(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + id + " is not found."));
        model.addAttribute("order", order);
        return "orderOverview";
    }
}
