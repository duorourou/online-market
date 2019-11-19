package com.thoughtworks.dddttt.onlinemarket.order.infra.po;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class AccountPO {

    @Column(name = "account_id")
    private String id;
}
