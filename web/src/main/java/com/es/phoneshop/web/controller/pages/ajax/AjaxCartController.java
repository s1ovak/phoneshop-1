package com.es.phoneshop.web.controller.pages.ajax;

import com.es.core.cart.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/ajaxCart")
public class AjaxCartController {
    @Resource
    private CartService cartService;

    @Resource
    private AddToCartValidator addToCartValidator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(addToCartValidator);
    }

    @PostMapping
    public @ResponseBody AddToCartResponse addPhone
            (@RequestBody @Validated AddToCartDto addToCartDto, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            cartService.addPhone(addToCartDto.getPhoneId(), Integer.parseInt(addToCartDto.getQuantity()));
            return new AddToCartResponse(null);
        }

        return new AddToCartResponse(bindingResult.getAllErrors().get(0).getDefaultMessage());
    }
}
