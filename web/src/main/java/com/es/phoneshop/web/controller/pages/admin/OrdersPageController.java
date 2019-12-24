package com.es.phoneshop.web.controller.pages.admin;

import com.es.core.model.order.Order;
import com.es.core.model.order.OrderService;
import com.es.core.model.order.OrderStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/admin/orders")
public class OrdersPageController {

    @Resource
    OrderService orderService;

    @GetMapping
    public String getOrders(Model model) {
        model.addAttribute("orders", orderService.getOrders());
        return "adminOrders";
    }

    @GetMapping(value = "/{id}")
    public String getOrder(Model model, @PathVariable Long id) {
        model.addAttribute("order", orderService.getOrder(id).orElse(new Order()));
        return "adminOrderDetails";
    }

    @PostMapping(value = "/{id}")
    public String setStatus(@PathVariable Long id, @RequestParam String status, Model model) {
        if (status.equals("delivered") || status.equals("rejected")) {
            orderService.setStatus(id, OrderStatus.valueOf(status.toUpperCase()));
        }
        return "redirect:/admin/orders/" + id;
    }
}
