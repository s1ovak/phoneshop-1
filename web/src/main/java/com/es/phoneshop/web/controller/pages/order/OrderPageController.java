package com.es.phoneshop.web.controller.pages.order;

import com.es.core.cart.CartService;
import com.es.core.model.order.Order;
import com.es.core.order.OrderService;
import com.es.core.order.OutOfStockException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/order")
public class OrderPageController {
    @Resource
    private OrderService orderService;

    @Resource
    private CartService cartService;

    @GetMapping
    public String getOrder(Model model) {
        Order newOrder = orderService.createOrder(cartService.getCart());
        model.addAttribute("order", newOrder);
        if (!model.containsAttribute("placeOrderDto")) {
            model.addAttribute("placeOrderDto", new PlaceOrderDto());
        }

        return "order";
    }

    @PostMapping
    public String placeOrder(@Valid @ModelAttribute PlaceOrderDto placeOrderDto,
                             BindingResult bindingResult,
                             Model model) {
        boolean containErrors = bindingResult.hasErrors();
        model.addAttribute("containErrors", containErrors);

        if (containErrors) {
            return "order";
        } else {
            return "redirect:/orderOverview";
        }
    }
}
