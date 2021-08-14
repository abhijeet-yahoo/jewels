package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetMt;

public interface IStnLooseRetMtRepository extends JpaRepository<StnLooseRetMt, Integer>, QueryDslPredicateExecutor<StnLooseRetMt> {

	StnLooseRetMt findByInvNo(String invNo);
}
