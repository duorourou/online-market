package com.thoughtworks.dddttt.onlinemarket.order.domain.entity.exception;

import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.SubtotalLimitation;
import java.math.BigDecimal;

public class SubtotalExceededLimitationException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE =
            "Order item subtotal could not greater than " + SubtotalLimitation.MAX.toPlainString()
                    + " and less than " + SubtotalLimitation.MIN.toPlainString() + ", current subtotal is %s.";

    public SubtotalExceededLimitationException(BigDecimal currentSubtotal) {
        super(String.format(MESSAGE_TEMPLATE, currentSubtotal.toPlainString()));
    }
}
