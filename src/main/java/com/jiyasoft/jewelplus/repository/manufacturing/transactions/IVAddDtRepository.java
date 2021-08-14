package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;

public interface IVAddDtRepository extends JpaRepository<VAddDt, Integer>,QueryDslPredicateExecutor<VAddDt>{
	
	List<VAddDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	VAddDt findByUniqueId(Long uniqueId);
	


}
