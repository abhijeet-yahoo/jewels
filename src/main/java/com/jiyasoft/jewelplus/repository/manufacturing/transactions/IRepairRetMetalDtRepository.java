package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.RepairRetMetalDt;

public interface IRepairRetMetalDtRepository extends JpaRepository<RepairRetMetalDt, Integer>,
QueryDslPredicateExecutor<RepairRetMetalDt>{

	List<RepairRetMetalDt>findByRepairRetDt(RepairRetDt repairRetDt);
	
	RepairRetMetalDt findByRepairRetDtAndPartNm(RepairRetDt repairRetDt,LookUpMast lookUpMast);
}
