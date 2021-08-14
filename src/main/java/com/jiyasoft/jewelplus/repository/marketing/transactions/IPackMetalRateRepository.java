package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMetalRate;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;

public interface IPackMetalRateRepository extends JpaRepository<PackMetalRate, Integer>,
QueryDslPredicateExecutor<PackMetalRate>{

PackMetalRate findByPackMtAndMetal(PackMt packMt,Metal metal);
	
	List<PackMetalRate> findByPackMt(PackMt packMt);
	

	
}
