package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignMoldType;

public interface IDesignMoldTypeRepository extends
		JpaRepository<DesignMoldType, Integer>,
		QueryDslPredicateExecutor<DesignMoldType> {

	DesignMoldType findByName(String name);

}
