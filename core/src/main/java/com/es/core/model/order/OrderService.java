package com.es.core.model.order;

import com.es.core.cart.Cart;
import com.es.core.model.order.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Cart cart);
    Long placeOrder(Order order);
    List<Long> findInvalidCartItems(Order order);
    Order updateOrder(Order order, Cart cart);
}
