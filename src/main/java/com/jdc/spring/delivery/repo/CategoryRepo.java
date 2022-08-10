package com.jdc.spring.delivery.repo;

import com.jdc.spring.delivery.entiity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
