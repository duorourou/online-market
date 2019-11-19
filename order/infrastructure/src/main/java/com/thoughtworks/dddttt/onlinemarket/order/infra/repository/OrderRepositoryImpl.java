package com.thoughtworks.dddttt.onlinemarket.order.infra.repository;

import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Order;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.repository.OrderRepository;
import com.thoughtworks.dddttt.onlinemarket.order.infra.po.OrderPO;
import com.thoughtworks.dddttt.onlinemarket.order.infra.repository.jpa.OrderJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;

    @Autowired
    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order store(Order order) {
        OrderPO orderPO = new OrderPO();
        // Mapping order domain entity to order PO
        orderJpaRepository.save(orderPO);
        // mapping persisted order PO to order domain entity
        order.setId(orderPO.getId());
        return order;
    }
}
