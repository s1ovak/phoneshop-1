package com.es.core.model.order;

import com.es.core.cart.Cart;
import com.es.core.model.order.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order createOrder(Cart cart);
    Long placeOrder(Order order);
    List<Long> findInvalidCartItems(Order order);
    Order updateOrder(Order order, Cart cart);
    Optional<Order> getOrder(Long orderId);
}
