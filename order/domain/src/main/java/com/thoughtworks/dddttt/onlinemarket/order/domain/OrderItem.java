package com.thoughtworks.dddttt.onlinemarket.order.domain;

import java.math.BigDecimal;

public class OrderItem {
    private final Product product;
    private int quantity;
    private SubtotalPrice subtotal;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = new SubtotalPrice(this.product.price().multiply(new BigDecimal(quantity)),
                SubtotalLimitation.MAX);
    }

    public OrderItem increaseProductNumber(int increasedQuantity) {
        int newQuantity = this.quantity + increasedQuantity;
        this.subtotal.amount(this.product.price().multiply(new BigDecimal(newQuantity)));
        this.quantity = newQuantity;
        return this;
    }
}
