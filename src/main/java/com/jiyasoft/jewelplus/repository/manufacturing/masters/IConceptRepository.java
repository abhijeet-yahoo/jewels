package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;

public interface IConceptRepository extends JpaRepository<Concept, Integer>, QueryDslPredicateExecutor<Concept> {

	Concept findByName(String name);
	

}
