package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.repository;

import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Order;

public interface OrderRepository {

    Order store(Order order);
}
