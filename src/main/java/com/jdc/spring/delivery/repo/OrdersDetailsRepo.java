package com.jdc.spring.delivery.repo;

import com.jdc.spring.delivery.entiity.OrdersDetails;
import com.jdc.spring.delivery.entiity.OrdersDetailsPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersDetailsRepo extends JpaRepository<OrdersDetails, OrdersDetailsPK> {

}
