package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.exception;

import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.OrderItemSizeLimitation;
import java.text.MessageFormat;

public class OrderItemCapacityLimitationException extends Exception {
    private static final String MESSAGE_PATTERN = "Order could only have {0} items";

    public OrderItemCapacityLimitationException() {
        super(MessageFormat.format(MESSAGE_PATTERN, OrderItemSizeLimitation.SIZE_LIMITATION));
    }
}
