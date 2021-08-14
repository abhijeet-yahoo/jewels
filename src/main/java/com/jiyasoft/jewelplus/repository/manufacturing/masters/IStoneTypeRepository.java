package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneType;

public interface IStoneTypeRepository extends
		JpaRepository<StoneType, Integer>, QueryDslPredicateExecutor<StoneType> {
	StoneType findByName(String name);
}
