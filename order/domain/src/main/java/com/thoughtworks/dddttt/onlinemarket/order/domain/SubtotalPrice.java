package com.thoughtworks.dddttt.onlinemarket.order.domain;

import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.SubtotalExceededLimitationException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class SubtotalPrice {

    private final BigDecimal originSubtotal;
    private final BigDecimal maxPrice;
    private final BigDecimal minPrice = SubtotalLimitation.MIN;

    public SubtotalPrice(BigDecimal newPrice, BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        if (exceededMaxPrice(newPrice) || exceededMinPrice(newPrice)) {
            throw new SubtotalExceededLimitationException(newPrice);
        }
        this.originSubtotal = newPrice;
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

    public SubtotalPrice amount(BigDecimal multiply) {
        return new SubtotalPrice(multiply, this.maxPrice);
    }
}
