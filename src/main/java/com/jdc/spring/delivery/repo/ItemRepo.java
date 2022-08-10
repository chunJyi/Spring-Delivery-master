package com.jdc.spring.delivery.repo;

import com.jdc.spring.delivery.entiity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ItemRepo extends JpaRepository<Item, Integer> {

	List<Item> findByQuery(String jpql, Map<String, Object> params);
	
	List<Item> findByCategoryId(Integer categoryId);
}
