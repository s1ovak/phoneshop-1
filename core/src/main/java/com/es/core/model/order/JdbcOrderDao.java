package com.es.core.model.order;

import com.es.core.model.phone.PhoneDao;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class JdbcOrderDao implements OrderDao {

    private final static String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE id = ?";

    private final static String FIND_ORDER_ITEMS_BY_ORDER_ID =
            "SELECT * from order_items where orderId = ?";

    private final static String GET_ALL_ORDERS_WITHOUT_ORDER_ITEMS = "SELECT * FROM orders";

    private final static String SET_STATUS =
            "UPDATE orders SET orderStatus = ? " +
            "WHERE id = ?";

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Resource
    private PhoneDao phoneDao;


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
            phoneDao.decreasePhoneStock(orderItem.getPhone().getId(), orderItem.getQuantity());
        });

        return orderId;
    }

    private void saveOrderItem(Long orderId, OrderItem orderItem) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("order_items");

        Map<String, Object> params = new HashMap<>();

        params.put("orderId", orderId);
        params.put("phoneId", orderItem.getPhone().getId());
        params.put("quantity", orderItem.getQuantity());

        simpleJdbcInsert.execute(params);
    }

    @Override
    public Optional<Order> getOrder(Long orderId) {
        try {
            Order order = jdbcTemplate.queryForObject(FIND_ORDER_BY_ID, new OrderRowMapper(), orderId);
            order.setOrderItems(getOrderItems(orderId));
            return Optional.of(order);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    private List<OrderItem> getOrderItems(Long orderId) {
        return jdbcTemplate.query(FIND_ORDER_ITEMS_BY_ORDER_ID, new OrderItemRowMapper(phoneDao), orderId);
    }

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query(GET_ALL_ORDERS_WITHOUT_ORDER_ITEMS, new OrderRowMapper());
    }

    @Override
    public void setStatus(Long id, OrderStatus status) {
        jdbcTemplate.update(SET_STATUS, status.toString(), id);
    }
}
