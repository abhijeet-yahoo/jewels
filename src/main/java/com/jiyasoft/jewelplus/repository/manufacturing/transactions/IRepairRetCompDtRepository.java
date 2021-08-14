package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;

public interface IRepairRetCompDtRepository extends JpaRepository<RepairRetCompDt, Integer>,QueryDslPredicateExecutor<RepairRetCompDt> {

	List<RepairRetCompDt>findByRepairRetDt(RepairRetDt repairRetDt);
}
