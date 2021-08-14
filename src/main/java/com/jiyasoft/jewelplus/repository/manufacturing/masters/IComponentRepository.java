package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;

public interface IComponentRepository extends
		JpaRepository<Component, Integer>, QueryDslPredicateExecutor<Component> {
	
	Component findByName(String name);
	
	List<Component> findByChargableAndDeactive(Boolean chargable,Boolean deactive);

}
