package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.PatternMast;

public interface IPatternMastRepository  extends JpaRepository<PatternMast, Integer>,
QueryDslPredicateExecutor<PatternMast>{
	
	PatternMast findByName(String name);

}
