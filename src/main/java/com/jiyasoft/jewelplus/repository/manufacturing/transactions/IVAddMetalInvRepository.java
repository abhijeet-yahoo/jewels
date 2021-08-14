package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;

public interface IVAddMetalInvRepository extends JpaRepository<VAddMetalInv, Integer>,
QueryDslPredicateExecutor<VAddMetalInv>{
	
	List<VAddMetalInv> findByCostingMt(CostingMt costingMt);
	
	VAddMetalInv findByMetalAndAdjustedAndCostingMt(Metal metal,Boolean adjusted,CostingMt costingMt);

}
