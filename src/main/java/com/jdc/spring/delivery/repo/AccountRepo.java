package com.jdc.spring.delivery.repo;

import com.jdc.spring.delivery.entiity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface AccountRepo extends JpaRepository<Account, Long> {
	Account findByEmail(String email);
	List<Account> findByQuery(String jpql, Map<String, Object> params);
}
