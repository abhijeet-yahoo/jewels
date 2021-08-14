package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetMt;

public interface IFgRetMtRepository extends JpaRepository<FgRetMt, Integer>, QueryDslPredicateExecutor<FgRetMt> {

}
