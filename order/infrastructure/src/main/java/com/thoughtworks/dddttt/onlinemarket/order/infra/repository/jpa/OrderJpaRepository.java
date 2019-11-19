package com.thoughtworks.dddttt.onlinemarket.order.infra.repository.jpa;

import com.thoughtworks.dddttt.onlinemarket.order.infra.po.OrderPO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderPO, String> {
}
