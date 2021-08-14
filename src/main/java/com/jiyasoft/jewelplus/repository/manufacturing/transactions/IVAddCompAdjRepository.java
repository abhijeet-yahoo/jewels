package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompAdj;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VAddCompInv;

public interface IVAddCompAdjRepository extends JpaRepository<VAddCompAdj, Integer>, QueryDslPredicateExecutor<VAddCompAdj>{
	
	List<VAddCompAdj> findByVAddCompInvAndDeactive(VAddCompInv vAddCompInv,Boolean deactive);

}
