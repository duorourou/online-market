package com.thoughtworks.ddd.ttt.onlinemarket.order.application.service;

import com.thoughtworks.ddd.ttt.onlinemarket.order.application.command.CreateOrderCommand;
import com.thoughtworks.ddd.ttt.onlinemarket.order.application.factory.OrderFactory;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Account;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Order;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.OrderItem;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.event.OrderCreatedEvent;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.repository.OrderRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

@Service
public class OrderApplicationService {

    private final ApplicationEventPublisher eventPublisher;
    private final OrderRepository orderRepository;

    public OrderApplicationService(ApplicationEventPublisher eventPublisher, OrderRepository orderRepository) {
        this.eventPublisher = eventPublisher;
        this.orderRepository = orderRepository;
    }

    public String createOrder(CreateOrderCommand createCommand, Account account) {

        Order order = OrderFactory.buildBy(createCommand, account);

        orderRepository.store(order);
        eventPublisher.publishEvent(new OrderCreatedEvent(order));
        return order.getId();
    }
}
