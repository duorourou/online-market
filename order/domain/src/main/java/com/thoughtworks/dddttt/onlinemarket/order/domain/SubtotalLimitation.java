package com.thoughtworks.dddttt.onlinemarket.order.domain;

import java.math.BigDecimal;

public class SubtotalLimitation {
    public static final BigDecimal MAX = new BigDecimal(1000);
    public static final BigDecimal MIN = BigDecimal.ZERO;
}
