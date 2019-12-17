package com.es.core.order;

import com.es.core.cart.Cart;
import com.es.core.model.order.Order;
import com.es.core.model.order.OrderItem;
import com.es.core.model.order.OrderStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@PropertySource("classpath:application.properties")
public class OrderServiceImpl implements OrderService {

    @Value("${delivery.price}")
    private Integer deliveryPrice;

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.setStatus(OrderStatus.NEW);

        BigDecimal subtotal = cart.getTotalPrice();
        BigDecimal deliveryPr = new BigDecimal(deliveryPrice);

        order.setSubtotal(subtotal);
        order.setDeliveryPrice(deliveryPr);
        order.setTotalPrice(subtotal.add(deliveryPr));

        cart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();

            orderItem.setPhone(cartItem.getPhone());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        });

        return order;
    }

    @Override
    public void placeOrder(Order order) throws OutOfStockException {
        throw new UnsupportedOperationException("TODO");
    }
}
