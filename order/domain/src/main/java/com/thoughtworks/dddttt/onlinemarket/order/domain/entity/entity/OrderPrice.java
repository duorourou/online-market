package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrderPrice {

    private final BigDecimal freight = new BigDecimal(20.00).setScale(2, RoundingMode.HALF_UP);
    private final BigDecimal originTotal;
    private final BigDecimal finalTotal;

    public OrderPrice(List<SubtotalPrice> allItemPrices) {
        this.originTotal = allItemPrices.stream().map(SubtotalPrice::originPrice).reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO)
                .add(freight)
                .setScale(2, RoundingMode.HALF_UP);
        this.finalTotal = amountFinalTotal();
    }

    private BigDecimal amountFinalTotal() {
        if (this.originTotal.compareTo(new BigDecimal(100)) > 0) {
            return originTotal.multiply(new BigDecimal(0.9)).setScale(2, RoundingMode.HALF_UP);
        }
        return originTotal;
    }

    public BigDecimal finalTotal() {
        return this.finalTotal;
    }

    public BigDecimal originTotal() {
        return this.originTotal;
    }

    public BigDecimal freight() {
        return this.freight;
    }
}
