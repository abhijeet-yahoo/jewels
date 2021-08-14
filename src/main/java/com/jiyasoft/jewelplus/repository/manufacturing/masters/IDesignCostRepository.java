package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCost;

public interface IDesignCostRepository extends
		JpaRepository<DesignCost, Integer>, QueryDslPredicateExecutor<Integer> {

	
	DesignCost findByDesignAndDeactive(Design design,Boolean deactive);
	
}
