package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity;

import lombok.Value;
import java.math.BigDecimal;

@Value
public class Product {

    private String id;
    private BigDecimal price;
    private String name;

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.price = price;
        this.name = name;
    }

    public BigDecimal price() {
        return this.price;
    }
}
