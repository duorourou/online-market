package com.thoughtworks.dddttt.onlinemarket.order.domain.entity


import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.SubtotalPrice
import spock.lang.Specification

class SubtotalPriceTest extends Specification {

    def "the subtotal price could not greater than max limitation"() {
        given:
          SubtotalPrice subtotal = new SubtotalPrice(BigDecimal.ZERO, 1, BigDecimal.ONE)

        when:
          subtotal.amount(BigDecimal.TEN, 1)

        then:
          com.thoughtworks.dddttt.onlinemarket.order.domain.entity.exception.SubtotalExceededLimitationException exception = thrown()
          exception != null
    }

    def "the subtotal price could not less than min limitation"() {
        given:
          SubtotalPrice subtotal = new SubtotalPrice(BigDecimal.ONE, 1, BigDecimal.ONE)

        when:
          subtotal.amount(BigDecimal.valueOf(-1.2), 1)

        then:
          com.thoughtworks.dddttt.onlinemarket.order.domain.entity.exception.SubtotalExceededLimitationException exception = thrown()
          exception != null
    }

    def "the subtotal price will be discounted"() {
        given: "the subtotal of an item"
          SubtotalPrice subtotalPrice = new SubtotalPrice(new BigDecimal(500), 1, null)

        when: "we get the discount price"
          BigDecimal discountedPrice = subtotalPrice.finalSubtotal()

        then: "the discount price will be calculated"
          discountedPrice == new BigDecimal(450.00)
    }
}
