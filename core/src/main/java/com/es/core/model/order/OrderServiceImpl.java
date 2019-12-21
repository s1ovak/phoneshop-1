package com.es.core.model.order;

import com.es.core.cart.Cart;
import com.es.core.model.phone.PhoneDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@PropertySource("classpath:application.properties")
public class OrderServiceImpl implements OrderService {

    @Value("${delivery.price}")
    private Integer deliveryPrice;

    @Resource
    private PhoneDao phoneDao;

    @Resource
    private OrderDao orderDao;

    @Resource
    private Cart cart;

    @Override
    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.setStatus(OrderStatus.NEW);

        BigDecimal subtotal = cart.getTotalPrice();
        BigDecimal deliveryPr = new BigDecimal(deliveryPrice);

        order.setSubtotal(subtotal);
        order.setDeliveryPrice(deliveryPr);
        order.setTotalPrice(subtotal.add(deliveryPr));
        order.setDate(LocalDateTime.now());
        setOrderItems(cart, order);

        return order;
    }

    @Override
    public List<Long> findInvalidCartItems(Order order) {
        List<Long> invalidPhoneIds = new ArrayList<>();

        order.getOrderItems().forEach(orderItem -> {
            Long phoneId = orderItem.getPhone().getId();

            if (orderItem.getQuantity() > phoneDao.getPhoneStock(phoneId)) {
                invalidPhoneIds.add(phoneId);
            }
        });

        return invalidPhoneIds;
    }

    @Override
    public Order updateOrder(Order order, Cart cart) {
        order.getOrderItems().clear();
        setOrderItems(cart, order);
        return order;
    }

    @Override
    public Long placeOrder(Order order) {
        long id = orderDao.saveOrder(order);
        cart.clearCart();
        return id;
    }


    private void setOrderItems(Cart cart, Order order) {
        cart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = new OrderItem();

            orderItem.setPhone(cartItem.getPhone());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setOrder(order);

            order.getOrderItems().add(orderItem);
        });
    }

    @Override
    public Optional<Order> getOrder(Long orderId) {
        return orderDao.getOrder(orderId);
    }
}
