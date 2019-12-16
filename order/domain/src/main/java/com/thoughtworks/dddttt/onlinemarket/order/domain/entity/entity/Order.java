package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity;

import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.exception.OrderItemCapacityLimitationException;
import java.util.Collections;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Order {
    private String id;
    private final Account account;
    private final List<OrderItem> items = new ArrayList<>();
    private OrderPrice orderPrice;

    public Order(Account account, List<OrderItem> items) {
        assert items != null && !items.isEmpty() : "items could not be empty.";
        this.account = account;
        this.items.addAll(items);
        amount();
    }

    private void amount() {
        this.orderPrice = new OrderPrice(this.items.stream()
                .map(OrderItem::subtotal).collect(Collectors.toList()));
    }

    public Order addItems(List<OrderItem> newItems) throws OrderItemCapacityLimitationException {
        if (OrderItemSizeLimitation.isExceededTheLimitation(currentItemSize() + newItems.size())) {
            throw new OrderItemCapacityLimitationException();
        }
        this.items.addAll(newItems);
        amount();
        return this;
    }

    private int currentItemSize() {
        return items.size();
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    public Order setId(String id) {
        this.id = id;
        return this;
    }

}
