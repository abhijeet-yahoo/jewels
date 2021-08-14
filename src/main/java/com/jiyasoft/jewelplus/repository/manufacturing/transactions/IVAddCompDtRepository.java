package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddDt;

public interface IVAddCompDtRepository extends JpaRepository<VAddCompDt, Integer>,QueryDslPredicateExecutor<VAddCompDt> {
	
	List<VAddCompDt> findByVAddDtAndDeactive(VAddDt vAddDt, Boolean deactive);

	List<VAddCompDt> findByComponentAndPurityAndColor(Component component,Purity purity,Color color);
	
	List<VAddCompDt> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
}
