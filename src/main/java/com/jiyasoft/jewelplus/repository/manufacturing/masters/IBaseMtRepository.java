package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BaseMt;

public interface IBaseMtRepository extends JpaRepository<BaseMt, Integer>,
		QueryDslPredicateExecutor<BaseMt> {
	
	public BaseMt findByBaseNo(Integer baseNo);

}
