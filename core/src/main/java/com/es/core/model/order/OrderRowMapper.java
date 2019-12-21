package com.es.core.model.order;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        Order order = new Order();

        order.setId(resultSet.getLong("id"));
        order.setSubtotal(resultSet.getBigDecimal("subtotal"));
        order.setDeliveryPrice(resultSet.getBigDecimal("deliveryPrice"));
        order.setFirstName(resultSet.getString("firstName"));
        order.setLastName(resultSet.getString("lastName"));
        order.setDeliveryAddress(resultSet.getString("deliveryAddress"));
        order.setContactPhoneNo(resultSet.getString("contactPhoneNo"));
        order.setStatus(OrderStatus.valueOf(resultSet.getString("orderStatus").toUpperCase()));
        order.setAdditionalInfo(resultSet.getString("additionalInfo"));
        order.setOrderItems(new ArrayList<>());
        order.setDate(resultSet.getTimestamp("date").toLocalDateTime());
        order.setTotalPrice(order.getSubtotal().add(order.getDeliveryPrice()));

        return order;
    }
}
