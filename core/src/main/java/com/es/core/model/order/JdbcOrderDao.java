package com.es.core.model.order;

import com.es.core.cart.Cart;
import com.es.core.model.phone.PhoneDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class JdbcOrderDao implements OrderDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private PhoneDao phoneDao;

    @Resource
    private Cart cart;

    @Override
    public Long saveOrder(Order order) {
        SimpleJdbcInsert orderSimpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();

        params.put("subtotal", order.getSubtotal());
        params.put("deliveryPrice", order.getDeliveryPrice());
        params.put("firstName", order.getFirstName());
        params.put("lastName", order.getLastName());
        params.put("deliveryAddress", order.getDeliveryAddress());
        params.put("contactPhoneNo", order.getContactPhoneNo());
        params.put("additionalInfo", order.getAdditionalInfo());
        params.put("orderStatus", order.getStatus().toString());
        params.put("date", order.getDate());

        Long orderId = orderSimpleJdbcInsert.executeAndReturnKey(params).longValue();

        order.getOrderItems().forEach(orderItem -> {
            saveOrderItem(orderId, orderItem);
        });

        cart.clearCart();
        return orderId;
    }

    private void saveOrderItem(Long orderId, OrderItem orderItem) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("order_items")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> params = new HashMap<>();

        params.put("orderId", orderId);
        params.put("phoneId", orderItem.getPhone().getId());
        params.put("quantity", orderItem.getQuantity());

        simpleJdbcInsert.execute(params);
        phoneDao.decreasePhoneStock(orderItem.getPhone().getId(), orderItem.getQuantity());
    }
}
