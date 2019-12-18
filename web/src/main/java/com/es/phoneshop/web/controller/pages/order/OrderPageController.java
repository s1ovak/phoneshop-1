package com.es.phoneshop.web.controller.pages.order;

import com.es.core.cart.CartService;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

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
            return getOrder(model);
        } else {
            Order order = orderService.createOrder(cartService.getCart());
            List<Long> invalidItemsPhoneIds = orderService.findInvalidCartItems(order);

            if (invalidItemsPhoneIds.isEmpty()) {
                setInfoIntoOrder(order, placeOrderDto);
                return "redirect:/orderOverview/" + orderService.placeOrder(order);
            } else {
                invalidItemsPhoneIds.forEach(item -> {
                    cartService.remove(item);
                });

                model.addAttribute("containQuantitiesErrors", true);
                return getOrder(model);
            }
        }
    }

    private void setInfoIntoOrder(Order order, PlaceOrderDto dto) {
        order.setFirstName(dto.getFirstName());
        order.setLastName(dto.getLastName());
        order.setDeliveryAddress(dto.getAddress());
        order.setContactPhoneNo(dto.getPhone());
        order.setAdditionalInfo(dto.getInfo());
    }
}
