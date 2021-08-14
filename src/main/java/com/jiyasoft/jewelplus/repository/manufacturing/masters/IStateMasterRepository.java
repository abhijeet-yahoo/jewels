package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.StateMaster;


public interface IStateMasterRepository extends JpaRepository<StateMaster, Integer>, QueryDslPredicateExecutor<StateMaster> {

	StateMaster findByName(String name); 
}
