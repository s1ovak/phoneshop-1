package com.es.phoneshop.web.controller.pages.compare;

import com.es.core.cart.CartService;
import com.es.core.model.phone.Phone;
import com.es.core.model.phone.PhoneService;
import com.es.phoneshop.web.controller.pages.ajax.AddToCartDto;
import com.es.phoneshop.web.controller.pages.ajax.AddToCartValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/compare")
public class ComparePageController {

    @Resource
    private CompareList compareList;

    @Resource
    private PhoneService phoneService;

    @Resource
    private CartService cartService;

    @Resource
    private AddToCartValidator addToCartValidator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(addToCartValidator);
    }

    @GetMapping
    public String getPhones(Model model) {
        List<Phone> phones = new ArrayList<>();

        compareList.getPhoneIds().forEach(id -> {
            phoneService.getPhoneById(id).ifPresent(phones::add);
        });

        model.addAttribute("phones", phones);
        cartService.insertMiniCart(model);
        if (!model.containsAttribute("addToCartDto")) {
            model.addAttribute("addToCartDto", new AddToCartDto());
        }
        return "compare";
    }

    @PostMapping(value = "/addToCart")
    public String addToCart(@ModelAttribute @Validated AddToCartDto addToCartDto,
                            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            cartService.addPhone(addToCartDto.getPhoneId(), Integer.parseInt(addToCartDto.getQuantity()));
        } else {
            model.addAttribute("errorPhoneId", addToCartDto.getPhoneId());
            model.addAttribute("errorMessage", bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return getPhones(model);
    }
}
