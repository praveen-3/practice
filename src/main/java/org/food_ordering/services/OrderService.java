package org.food_ordering.services;

import java.util.HashMap;
import java.util.Map;

import org.food_ordering.models.Order;


public class OrderService {
    private final Map<String, Order> orders;

    public OrderService() {
        this.orders = new HashMap<>();
    }

    public void createOrder(Order order) {
        orders.put(order.orderId(), order);
    }

    public Order getOrder(String orderId) {
        return orders.get(orderId);
    }
}
