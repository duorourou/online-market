package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.event;

import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Account;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Order;
import lombok.Value;
import java.math.BigDecimal;

@Value
public class OrderCreatedEvent {

    private final Account account;
    private final String orderId;
    private final BigDecimal totalPrice;


    public OrderCreatedEvent(Account account, String orderId, BigDecimal totalPrice) {
        this.account = account;
        this.orderId = orderId;
        this.totalPrice = totalPrice;
    }

    public OrderCreatedEvent(Order order) {
        this.account = order.getAccount();
        this.orderId = order.getId();
        this.totalPrice = order.getOrderPrice().finalTotal();
    }
}
