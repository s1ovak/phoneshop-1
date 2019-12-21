package com.es.core.cart;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface CartService {

    Cart getCart();

    void addPhone(Long phoneId, Integer quantity);

    /**
     * @param items
     * key: {@link com.es.core.model.phone.Phone#id}
     * value: quantity
     */
    void update(Map<Long, Integer> items);

    void remove(Long phoneId);

    void insertMiniCart(Model model);


}
