package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StyleChange;

public interface IStyleChangeRepository extends
		JpaRepository<StyleChange, Integer>,
		QueryDslPredicateExecutor<StyleChange> {
	
	

	StyleChange findByDesign(Design design);

}
