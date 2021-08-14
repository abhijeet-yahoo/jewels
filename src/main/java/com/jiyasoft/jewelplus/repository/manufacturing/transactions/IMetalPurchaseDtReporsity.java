package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalPurchaseMt;

public interface IMetalPurchaseDtReporsity extends JpaRepository<MetalPurchaseDt, Integer>, QueryDslPredicateExecutor<MetalPurchaseDt>{

	List<MetalPurchaseDt> findByMetalPurchaseMt(MetalPurchaseMt MetalPurchaseMt);

	Page<MetalPurchaseDt> findByMetalPurchaseMt(MetalPurchaseMt metalPurchaseMt,Pageable pageable);
	
	List<MetalPurchaseDt> findByMetalPurchaseMtAndDeactive(MetalPurchaseMt metalPurchaseDtMt, Boolean deactive);
	
	MetalPurchaseDt findByMetalPurchaseMtAndMetal(MetalPurchaseMt metalPurchaseMt,Metal metal); 
	
	List<MetalPurchaseDt> findByMetalAndDeactive(Metal metal,Boolean deactive);

}
