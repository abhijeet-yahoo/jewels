package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairIssMt;

public interface IRepairIssMtRepository extends JpaRepository<RepairIssMt, Integer>,
QueryDslPredicateExecutor<RepairIssMt> {

	RepairIssMt findByInvNoAndDeactive(String invNo,Boolean deactive);
}
