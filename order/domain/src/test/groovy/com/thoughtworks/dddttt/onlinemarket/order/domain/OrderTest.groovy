package com.thoughtworks.dddttt.onlinemarket.order.domain


import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.OrderItemCapacityLimitationException
import spock.lang.Specification

import java.util.stream.IntStream

class OrderTest extends Specification {

    def "order can add order items"() {
        given: "we have an order"
          Order order = new Order()

        and: "we have some order items"
          def items = [
                  new OrderItem(),
                  new OrderItem()
          ]

        when: "we add order items to order"
          order.addItems(items)

        then: "the order items have been added to the order"
          order.getItems().containsAll(items)
    }

    def "order only could have less than 100 items"() {
        given: "we have an order"
          Order order = new Order()

        and: "the order has already include 99 items"
          List<OrderItem> items = IntStream.range(0, 99).mapToObj { i -> new OrderItem() } collect()
          order.addItems(items)

        and: "we have a new order item"
          OrderItem item = new OrderItem()
        when:
          order.addItems([item])

        then: "there will be an OrderItemCapacityLimitation error"
          OrderItemCapacityLimitationException exception = thrown()
    }
}
