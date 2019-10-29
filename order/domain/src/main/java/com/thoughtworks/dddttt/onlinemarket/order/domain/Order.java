package com.thoughtworks.dddttt.onlinemarket.order.domain;

import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.OrderItemCapacityLimitationException;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();

    public Order addItems(List<OrderItem> newItems) throws OrderItemCapacityLimitationException {
        if (OrderItemSizeLimitation.isExceededTheLimitation(currentItemSize() + newItems.size())) {
            throw new OrderItemCapacityLimitationException();
        }
        this.items.addAll(newItems);
        return this;
    }

    private int currentItemSize() {
        return items.size();
    }

    public List<OrderItem> getItems() {
        return this.items;
    }


}
