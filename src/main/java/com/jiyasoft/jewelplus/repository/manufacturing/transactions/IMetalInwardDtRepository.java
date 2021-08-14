
package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;

public interface IMetalInwardDtRepository extends
		JpaRepository<MetalInwardDt, Integer>,
		QueryDslPredicateExecutor<MetalInwardDt> {

	List<MetalInwardDt> findByMetalInwardMt(MetalInwardMt metalInwardMt);
	
	MetalInwardDt findByUniqueId(Long uniqueId);
	
	
	Page<MetalInwardDt> findByMetalInwardMt(MetalInwardMt metalInwardMt,Pageable pageable);
	
	MetalInwardDt findByMetalInwardMtAndMetal(MetalInwardMt metalInwardMt,Metal metal); 
	
	List<MetalInwardDt> findByMetalInwardMtAndDeactive(MetalInwardMt metalInwardMt, Boolean deactive);
	
	List<MetalInwardDt> findByMetalAndDeactive(Metal metal,Boolean deactive);

}
