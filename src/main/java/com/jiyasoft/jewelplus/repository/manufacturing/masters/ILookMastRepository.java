package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookMast;

public interface ILookMastRepository extends JpaRepository<LookMast, Integer>,
QueryDslPredicateExecutor<LookMast>{

	LookMast findByName(String lookmastName);

}


