package com.es.phoneshop.web.controller.pages;

import com.es.core.cart.CartService;
import com.es.core.model.phone.PhoneDao;
import com.es.core.model.phone.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/productList")
public class ProductListPageController {
    @Resource
    private PhoneService phoneService;

    @Resource
    private CartService cartService;

    @GetMapping
    public String showProductList(@RequestParam(value = "query", required = false) String query,
                                  @RequestParam(value = "sort", required = false) String sort,
                                  @RequestParam(value = "order", required = false) String order,
                                  @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                                  Model model) {
        model.addAttribute("phones", phoneService.findPhones(query, sort, order, page));
        cartService.insertMiniCart(model);
        return "productList";
    }
}
