package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;

public interface IVAddCompInvRepository extends JpaRepository<VAddCompInv, Integer>,QueryDslPredicateExecutor<VAddCompInv> {
	
	List<VAddCompInv> findByCostingMtAndDeactive(CostingMt costingMt,Boolean deactive);
	
	VAddCompInv findByComponentAndPurityAndColorAndAdjustedAndDeactive(Component component,Purity purity,Color color,Boolean adjusted,Boolean deactive);
	
}
