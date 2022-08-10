package com.jdc.spring.delivery.repo;

import com.jdc.spring.delivery.entiity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface OrdersRepo extends JpaRepository<Orders, Long> {

	List<Orders> findByCustomerEmail(String email);
	List<Orders> findByQuery(String jpql, Map<String, Object> params);
	
}
