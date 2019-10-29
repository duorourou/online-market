package com.thoughtworks.dddttt.onlinemarket.order.domain.exception;

import java.text.MessageFormat;

public class OrderItemCapacityLimitationException extends Exception {
    private static final String MESSAGE_PATTERN = "Order could only have %d items";

    public OrderItemCapacityLimitationException(int limitSize) {
        super(MessageFormat.format(MESSAGE_PATTERN, limitSize));
    }
}
