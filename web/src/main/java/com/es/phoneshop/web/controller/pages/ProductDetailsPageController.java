package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
@RequestMapping(value = "/productDetails")
public class ProductDetailsPageController {

    @Resource
    private PhoneService phoneService;

    @Resource
    private CartService cartService;

    @GetMapping(value = "/{id}")
    public String getPhone(@PathVariable(name = "id") Long id, Model model) {
        Optional<Phone> phone = phoneService.getPhoneById(id);
        model.addAttribute("phone", phone.orElse(null));
        cartService.insertMiniCart(model);
        return "productDetails";
    }
}
