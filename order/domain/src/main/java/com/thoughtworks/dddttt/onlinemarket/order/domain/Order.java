package com.thoughtworks.dddttt.onlinemarket.order.domain;

import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.OrderItemCapacityLimitationException;
import java.util.ArrayList;
import java.util.List;

import static com.thoughtworks.dddttt.onlinemarket.order.domain.OrderItemSizeLimitation.SIZE_LIMITATION;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();

    public Order addItems(List<OrderItem> newItems) throws OrderItemCapacityLimitationException {
        if(items.size() + newItems.size() >= SIZE_LIMITATION) {
            throw new OrderItemCapacityLimitationException(SIZE_LIMITATION);
        }
        this.items.addAll(newItems);
        return this;
    }

    public List<OrderItem> getItems() {
        return this.items;
    }
}
