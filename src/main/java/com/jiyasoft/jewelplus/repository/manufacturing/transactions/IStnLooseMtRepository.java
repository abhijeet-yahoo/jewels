package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;

public interface IStnLooseMtRepository extends JpaRepository<StnLooseMt, Integer>,QueryDslPredicateExecutor<StnLooseMt>{

	StnLooseMt findByInvNo(String invNo);
}
