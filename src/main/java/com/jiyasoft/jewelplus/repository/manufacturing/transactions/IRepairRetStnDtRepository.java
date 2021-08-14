package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetStnDt;

public interface IRepairRetStnDtRepository extends JpaRepository<RepairRetStnDt, Integer>,QueryDslPredicateExecutor<RepairRetStnDt>{

	List<RepairRetStnDt>findByRepairRetDt(RepairRetDt repairRetDt);
}
