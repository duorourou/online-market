package com.thoughtworks.dddttt.onlinemarket.order.domain;

import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.SubtotalExceededLimitationException;
import java.math.BigDecimal;

public class OrderItem {
    private final Product product;
    private int quantity;
    private BigDecimal subtotal;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.subtotal = calculateSubtotal(this.product.price(), this.quantity);
    }

    public OrderItem increaseProductNumber(int increasedQuantity) {
        int newQuantity = this.quantity + increasedQuantity;
        this.subtotal = calculateSubtotal(this.product.price(), newQuantity);
        this.quantity = newQuantity;
        return this;
    }

    private BigDecimal calculateSubtotal(BigDecimal price, int quantity) {
        BigDecimal subtotal = price.multiply(new BigDecimal(quantity));
        if (subtotal.compareTo(SubtotalLimitation.LIMITATION) > 0) {
            throw new SubtotalExceededLimitationException(subtotal);
        }
        return subtotal;
    }
}
