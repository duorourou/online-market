package com.thoughtworks.dddttt.onlinemarket.order.domain

import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.SubtotalExceededLimitationException
import spock.lang.Specification

class SubtotalPriceTest extends Specification {

    def "the subtotal price could not greater than max limitation"() {
        given:
          SubtotalPrice subtotal = new SubtotalPrice(BigDecimal.ZERO, BigDecimal.ONE)

        when:
          subtotal.amount(BigDecimal.TEN)

        then:
          SubtotalExceededLimitationException exception = thrown()
          exception != null
    }

    def "the subtotal price could not less than min limitation"() {
        given:
          SubtotalPrice subtotal = new SubtotalPrice(BigDecimal.ONE, BigDecimal.ONE)

        when:
          subtotal.amount(BigDecimal.valueOf(-1.2))

        then:
          SubtotalExceededLimitationException exception = thrown()
          exception != null
    }

    def "the subtotal price will be discounted"() {
        given: "the subtotal of an item"
          SubtotalPrice subtotalPrice = new SubtotalPrice(new BigDecimal(500), null)

        when: "we get the discount price"
          BigDecimal discountedPrice = subtotalPrice.finalSubtotal()

        then: "the discount price will be calculated"
          discountedPrice == new BigDecimal(450.00)
    }
}
