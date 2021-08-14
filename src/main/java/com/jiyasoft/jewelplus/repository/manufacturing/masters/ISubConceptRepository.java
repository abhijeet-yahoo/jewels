package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Concept;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubConcept;

public interface ISubConceptRepository extends
		JpaRepository<SubConcept, Integer>,
		QueryDslPredicateExecutor<SubConcept> {

	SubConcept findByName(String name);

	Page<SubConcept> findByConcept(Concept concept, Pageable pageable);
	
	SubConcept findByConceptAndName(Concept concept,String name);
	
	List<SubConcept> findByDeactive(Boolean deactive);

}
