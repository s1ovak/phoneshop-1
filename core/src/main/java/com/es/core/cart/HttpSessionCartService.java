package com.es.core.cart;

import com.es.core.model.phone.PhoneDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class HttpSessionCartService implements CartService {

    @Resource
    PhoneDao phoneDao;

    @Resource
    Cart cart;

    @Override
    public void insertMiniCart(Model model) {
        model.addAttribute("totalPrice", cart.getTotalPrice());
        model.addAttribute("totalQuantity", cart.getTotalQuantity());
    }

    @Override
    public Cart getCart() {
        return cart;
    }

    @Override
    @Transactional
    public void addPhone(Long phoneId, Integer quantity) {
        if (quantity > 0) {
            phoneDao.get(phoneId)
                    .ifPresent(phone -> cart.addCartItem(new CartItem(phone, quantity)));
        }
    }

    @Override
    @Transactional
    public void update(Map<Long, Integer> items) {
        items.forEach((key, value) -> cart.setCartItemQuantity(key, value));
    }

    @Override
    @Transactional
    public void remove(Long phoneId) {
        if(phoneId > 0) {
            cart.removeItem(phoneId);
        }
    }
}
