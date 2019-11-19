package com.thoughtworks.ddd.ttt.onlinemarket.order.application.command;

import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;
import java.util.List;

@AllArgsConstructor
@Getter
public class CreateOrderCommand {

    private final List<OrderItem> items;

    @Value
    public static class OrderItem {
        private final Product product;
        private final int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }

}
