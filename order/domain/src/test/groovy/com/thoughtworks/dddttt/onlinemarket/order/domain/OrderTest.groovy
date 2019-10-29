package com.thoughtworks.dddttt.onlinemarket.order.domain


import com.thoughtworks.dddttt.onlinemarket.order.domain.exception.OrderItemCapacityLimitationException
import spock.lang.Specification

import java.util.stream.IntStream

import static com.thoughtworks.dddttt.onlinemarket.order.domain.OrderItemSizeLimitation.SIZE_LIMITATION

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

    def 'we have a limited order items in an order'() {
        given: "we have an order"
          Order order = new Order()

        and: "the items size in this order has already reached the limitation "
          List<OrderItem> items = IntStream.range(0, SIZE_LIMITATION).mapToObj { i -> new OrderItem() }.collect()
          order.addItems(items)

        and: "we have a new order item"
          OrderItem item = new OrderItem()
        when: "we try to add the new item to this order"
          order.addItems([item])

        then: "there will be an OrderItemCapacityLimitation error"
          OrderItemCapacityLimitationException exception = thrown()
          exception != null
    }
}
