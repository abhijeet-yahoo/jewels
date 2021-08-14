package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMt;

public interface IRepairRetDtRepository extends JpaRepository<RepairRetDt, Integer>,
QueryDslPredicateExecutor<RepairRetDt> {
	
	List<RepairRetDt> findByRepairRetMt(RepairRetMt repairRetMt);
	

}
