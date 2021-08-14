package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignGroup;

public interface IDesignGroupRepository extends
		JpaRepository<DesignGroup, Integer>,
		QueryDslPredicateExecutor<DesignGroup> {

	DesignGroup findByName(String name);

}
