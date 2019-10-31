package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity;

public class OrderItem {
    private final Product product;
    private int quantity;
    private SubtotalPrice subtotal;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = new SubtotalPrice(this.product.price(), quantity,
                SubtotalLimitation.MAX);
    }

    protected OrderItem increaseProductNumber(int increasedQuantity) {
        int newQuantity = this.quantity + increasedQuantity;
        this.subtotal.amount(this.product.price(), newQuantity);
        this.quantity = newQuantity;
        return this;
    }

    protected SubtotalPrice subtotal() {
        return this.subtotal;
    }
}
