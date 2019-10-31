package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity;

public final class OrderItemSizeLimitation {
    public static final int SIZE_LIMITATION = 100;

    public static boolean isExceededTheLimitation(int orderItemSize) {
        return orderItemSize > SIZE_LIMITATION;
    }
}
