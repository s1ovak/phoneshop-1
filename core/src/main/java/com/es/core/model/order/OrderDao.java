package com.es.core.model.order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    Long saveOrder(Order order);
    Optional<Order> getOrder(Long orderId);
    List<Order> getAll();
    void setStatus(Long id, OrderStatus status);
}
