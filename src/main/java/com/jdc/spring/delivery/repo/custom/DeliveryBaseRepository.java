package com.jdc.spring.delivery.repo.custom;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class DeliveryBaseRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {
	
	private final EntityManager em;
	private final JpaEntityInformation<T, ID> info;
	
	public DeliveryBaseRepository(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
		this.info = entityInformation;
	}
	
	public List<T> findByQuery(String jpql, Map<String, Object> params) {
		
		TypedQuery<T> query = em.createQuery(jpql, info.getJavaType());
		
		for(String key : params.keySet()) {
			query.setParameter(key, params.get(key));
		}
		
		return query.getResultList();
	}

}
