package com.thoughtworks.dddttt.onlinemarket.order.infra.po;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItemPO {
    @Id
    @GeneratedValue
    private String id;

    @Embedded
    private ProductPO product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderPO order;
}
