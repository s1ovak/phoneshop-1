package com.es.core.model.order;

import java.util.Optional;

public interface OrderDao {
    Long saveOrder(Order order);
    Optional<Order> getOrder(Long orderId);
}
