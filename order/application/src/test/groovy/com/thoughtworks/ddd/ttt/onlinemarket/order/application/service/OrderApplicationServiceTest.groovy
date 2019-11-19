package com.thoughtworks.ddd.ttt.onlinemarket.order.application.service

import com.thoughtworks.ddd.ttt.onlinemarket.order.application.command.CreateOrderCommand
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Account
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Order
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.entity.Product
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.event.OrderCreatedEvent
import com.thoughtworks.dddttt.onlinemarket.order.domain.entity.repository.OrderRepository
import org.springframework.context.ApplicationEventPublisher
import spock.lang.Specification

class OrderApplicationServiceTest extends Specification {


    def eventPublisher = Mock(ApplicationEventPublisher)

    def orderRepository = Mock(OrderRepository)

    OrderApplicationService orderApplicationService

    public void setup() {
        orderApplicationService = new OrderApplicationService(eventPublisher, orderRepository)
    }


    def "create an order by an account"() {
        given: "an account"
          Account account = new Account("ACC-001")

        and: "order information"
          CreateOrderCommand createCommand = new CreateOrderCommand([
                  new CreateOrderCommand.OrderItem(new Product("P001", "Chrome Extension1",
                          new BigDecimal("10.10")), 1),
                  new CreateOrderCommand.OrderItem(new Product("P002", "Chrome Extension2",
                          new BigDecimal("10.10")), 1),
                  new CreateOrderCommand.OrderItem(new Product("P003", "Chrome Extension3",
                          new BigDecimal("10.10")), 1)
          ])

        when: "this account creates order with the order information"
          orderApplicationService.createOrder(createCommand, account)

        then: "order will be created"
          1 * orderRepository.store({
              verifyAll(it, Order) {
                  it.account == account
                  it.getItems().size() == createCommand.items.size()
              }
              it
          } as Order)

        and: "there will be a OrderCreatedEvent has been published"
          1 * eventPublisher.publishEvent({
              verifyAll(it, OrderCreatedEvent) {
                  it instanceof OrderCreatedEvent
                  it.account.accountId == "ACC-001"
                  it.totalPrice == new BigDecimal("50.30") // with freight
              }
              it
          } as OrderCreatedEvent)

    }
}
