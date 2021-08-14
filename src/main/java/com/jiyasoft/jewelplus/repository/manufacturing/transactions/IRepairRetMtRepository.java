package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMt;

public interface IRepairRetMtRepository extends JpaRepository<RepairRetMt, Integer>,
QueryDslPredicateExecutor<RepairRetMt>{

}
