package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CostingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddMetalInv;

public interface IVAddMetalAdjRepository extends JpaRepository<VAddMetalAdj, Integer>,
QueryDslPredicateExecutor<VAddMetalAdj>{
	
	List<VAddMetalAdj> findByVAddMetalInv(VAddMetalInv vAddMetalInv);
	
	VAddMetalAdj findByCostingMtAndVAddMetalInv(CostingMt costingMt,VAddMetalInv vAddMetalInv);

}
