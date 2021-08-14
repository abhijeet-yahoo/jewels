package com.jiyasoft.jewelplus.repository.manufacturing.transactions;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VaddMetalDt;


public interface IVaddMetalDtRepository extends JpaRepository<VaddMetalDt, Integer>, QueryDslPredicateExecutor<VaddMetalDt> {
	
	List<VaddMetalDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	List<VaddMetalDt> findByVAddDtAndDeactive(VAddDt vAddDt,Boolean deactive);
	
	VaddMetalDt findByVAddDtAndDeactiveAndPartNm(VAddDt vAddDt,Boolean deactive,LookUpMast lookUpMast);

}
