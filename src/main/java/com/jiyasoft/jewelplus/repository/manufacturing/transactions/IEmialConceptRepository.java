package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.EmailConcept;

public interface IEmialConceptRepository extends
		JpaRepository<EmailConcept, Integer>,
		QueryDslPredicateExecutor<EmailConcept> {
	
	public EmailConcept findByName(String name);

}
