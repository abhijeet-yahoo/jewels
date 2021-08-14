package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.InwardType;

public interface IInwardTypeRepository extends
		JpaRepository<InwardType, Integer>,
		QueryDslPredicateExecutor<InwardType> {

	InwardType findByName(String name);

}
