package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity;

import java.math.BigDecimal;

public class Product {

    private BigDecimal price;
    private String name;

    public Product(String name, BigDecimal price) {
        this.price = price;
        this.name = name;
    }

    public BigDecimal price() {
        return this.price;
    }
}
