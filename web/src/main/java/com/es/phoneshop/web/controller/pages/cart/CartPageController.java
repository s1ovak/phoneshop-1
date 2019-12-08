package com.es.phoneshop.web.controller.pages.cart;

import com.es.core.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/cart")
public class CartPageController {
    @Resource
    private CartService cartService;

    @Resource
    private UpdateCartValidator updateCartValidator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(updateCartValidator);
    }

    @GetMapping
    public String getCart(Model model) {
        model.addAttribute("cartItems", cartService.getCart().getCartItems());
        cartService.insertMiniCart(model);
        if (!model.containsAttribute("updateCartDto")) {
            model.addAttribute("updateCartDto", new UpdateCartDto());
        }
        return "cart";
    }

    @PostMapping(value = "/update")
    public String updateCart(
            @ModelAttribute @Validated UpdateCartDto updateCartDto,
            BindingResult bindingResult, Model model) {
        boolean containErrors = bindingResult.hasErrors();
        model.addAttribute("containErrors", containErrors);

        if (containErrors) {
            return getCart(model);
        } else {
            Map<Long, Integer> items  = new HashMap<>();
            updateCartDto.getItems().forEach((key, value) -> {
                items.put(key, Integer.parseInt(value));
            });
            cartService.update(items);
            return "redirect:/cart";
        }
    }

    @PostMapping(value = "delete/{id}")
    public String deleteCartItem(@PathVariable(name = "id") Long phoneId) {
        cartService.remove(phoneId);
        return "redirect:/cart";
    }
}
