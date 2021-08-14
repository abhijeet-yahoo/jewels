package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardMt;

public interface IMetalOutwardDtRepository extends
		JpaRepository<MetalOutwardDt, Integer>,
		QueryDslPredicateExecutor<MetalOutwardDt> {
	
	
	 List<MetalOutwardDt> findByMetalOutwardMt(MetalOutwardMt metalOutwardMt);

	 Page<MetalOutwardDt> findByMetalOutwardMt(MetalOutwardMt metalOutwardMt,
			Pageable pageable);
	

	 List<MetalOutwardDt> findByMetalOutwardMtAndDeactive(MetalOutwardMt metalOutwardMt, Boolean deactive);

	 
	 MetalOutwardDt findByUniqueId(Long uniqueId);


}
