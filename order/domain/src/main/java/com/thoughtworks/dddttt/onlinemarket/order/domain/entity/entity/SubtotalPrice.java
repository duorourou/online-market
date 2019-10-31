package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity;

import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.exception.SubtotalExceededLimitationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class SubtotalPrice {

    private final BigDecimal originSubtotal;
    private final BigDecimal maxPrice;
    private final BigDecimal minPrice = SubtotalLimitation.MIN;

    public SubtotalPrice(BigDecimal unitPrice, int quantity, BigDecimal maxPrice) {
        this.maxPrice = maxPrice;

        BigDecimal subtotal = unitPrice.multiply(new BigDecimal(quantity));
        if (exceededMaxPrice(subtotal) || exceededMinPrice(subtotal)) {
            throw new SubtotalExceededLimitationException(subtotal);
        }
        this.originSubtotal = subtotal;
    }

    private boolean exceededMinPrice(BigDecimal newPrice) {
        return newPrice.compareTo(minPrice) < 0;
    }

    private boolean exceededMaxPrice(BigDecimal newPrice) {
        return hasPriceLimit() && newPrice.compareTo(maxPrice) > 0;
    }

    private boolean hasPriceLimit() {
        return Objects.nonNull(this.maxPrice);
    }

    public BigDecimal finalSubtotal() {
        return originSubtotal.multiply(new BigDecimal(0.9)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal originPrice() {
        return this.originSubtotal;
    }

    public SubtotalPrice amount(BigDecimal unitPrice, int quantity) {
        return new SubtotalPrice(unitPrice, quantity, this.maxPrice);
    }
}
