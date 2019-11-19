package com.thoughtworks.dddttt.onlinemarket.order.infra.po;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "order")
@Getter
@Setter
public class OrderPO {
    @Id
    @GeneratedValue
    private String id;

    private String totalPrice;

    @Embedded
    private AccountPO account;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<OrderItemPO> items;
}
