package com.thoughtworks.dddttt.onlinemarket.order.domain;

import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.SubtotalExceededLimitationException;
import java.math.BigDecimal;
import java.util.Objects;

public class SubtotalPrice {

    private BigDecimal price;
    private final BigDecimal maxPrice;
    private final BigDecimal minPrice = SubtotalLimitation.MIN;

    public SubtotalPrice(BigDecimal newPrice, BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
        amount(newPrice);
    }

    public void amount(BigDecimal newPrice) {
        if (exceededMaxPrice(newPrice) || exceededMinPrice(newPrice)) {
            throw new SubtotalExceededLimitationException(newPrice);
        }
        this.price = newPrice;
    }

    private boolean exceededMinPrice(BigDecimal newPrice) {
        return newPrice.compareTo(minPrice) < 0;
    }

    private boolean exceededMaxPrice(BigDecimal newPrice) {
        return !hasPriceLimit() || newPrice.compareTo(maxPrice) > 0;
    }

    private boolean hasPriceLimit() {
        return Objects.nonNull(this.maxPrice);
    }
}
