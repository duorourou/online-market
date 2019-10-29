package com.thoughtworks.dddttt.onlinemarket.order.domain.exception;

import com.thoughtworks.dddttt.onlinemarket.order.domain.SubtotalLimitation;
import java.math.BigDecimal;
import java.text.MessageFormat;

public class SubtotalExceededLimitationException extends RuntimeException {
    private static final String MESSAGE_TEMPLATE =
            "Order item subtotal could not greater than " + SubtotalLimitation.LIMITATION.toPlainString()
                    + ", current subtotal is %d.";

    public SubtotalExceededLimitationException(BigDecimal currentSubtotal) {
        super(MessageFormat.format(MESSAGE_TEMPLATE, currentSubtotal));
    }
}
