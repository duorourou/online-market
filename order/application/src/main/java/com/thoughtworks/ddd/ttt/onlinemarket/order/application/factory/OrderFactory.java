package com.thoughtworks.ddd.ttt.onlinemarket.order.application.factory;

import com.thoughtworks.ddd.ttt.onlinemarket.order.application.command.CreateOrderCommand;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Account;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Order;
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.OrderItem;
import java.util.stream.Collectors;

public class OrderFactory {

  public static Order buildBy(CreateOrderCommand createCommand,
      Account account) {
    return new Order(account, createCommand.getItems()
        .stream().map(item -> new OrderItem(item.getProduct(), item.getQuantity()))
        .collect(Collectors.toList()));
  }
}
