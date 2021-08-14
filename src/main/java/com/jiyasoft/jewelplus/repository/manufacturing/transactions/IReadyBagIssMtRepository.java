package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssMt;

public interface IReadyBagIssMtRepository extends JpaRepository<ReadyBagIssMt, Integer>, QueryDslPredicateExecutor<ReadyBagIssMt>{

}

