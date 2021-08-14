package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;

public interface IPurityRepository extends JpaRepository<Purity, Integer>,
		QueryDslPredicateExecutor<Purity> {

	Purity findByName(String name);

	Page<Purity> findByMetal(Metal metal, Pageable pageable);
	
	Purity findByMetalAndPure(Metal metal, Boolean pure);

	Purity findByMetalAndDefPurity(Metal metal, Boolean defPurity);
	
	Purity findByDefPurityAndDeactive(Boolean defPurity,Boolean deactive);

}