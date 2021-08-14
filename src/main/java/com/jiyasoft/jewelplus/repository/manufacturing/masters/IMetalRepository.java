package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;

public interface IMetalRepository extends JpaRepository<Metal, Integer>,
		QueryDslPredicateExecutor<Metal> {
	Metal findByName(String name);

}
