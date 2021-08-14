package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompOutwardMt;

public interface ICompOutwardDtRepository extends JpaRepository<CompOutwardDt, Integer>,
QueryDslPredicateExecutor<CompOutwardDt>{
	
	 List<CompOutwardDt> findByCompOutwardMt(CompOutwardMt compOutwardMt);

	public Page<CompOutwardDt> findByCompOutwardMt(CompOutwardMt compOutwardMt,
			Pageable pageable);
	
	public CompOutwardDt findByUniqueId(Long uniqueId);
	
	 List<CompOutwardDt> findByCompOutwardMtAndDeactive(CompOutwardMt compOutwardMt, Boolean deactive);


}
