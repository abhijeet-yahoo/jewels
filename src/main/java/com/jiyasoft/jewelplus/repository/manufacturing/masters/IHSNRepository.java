package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.HSNMast;

public interface IHSNRepository extends JpaRepository<HSNMast, Integer>, QueryDslPredicateExecutor<HSNMast> {
	
	HSNMast findById(Integer id);

	HSNMast findByCodeAndDeactive(String code, Boolean deactive);
	
	
}
