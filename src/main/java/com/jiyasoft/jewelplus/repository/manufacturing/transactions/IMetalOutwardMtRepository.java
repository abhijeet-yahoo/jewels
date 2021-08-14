package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalInwardMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MetalOutwardMt;

public interface IMetalOutwardMtRepository extends JpaRepository<MetalOutwardMt, Integer> ,
QueryDslPredicateExecutor<MetalOutwardMt>{
	
	MetalOutwardMt findByInvNo(String invNo);
	
    public MetalOutwardMt findById(Integer id);
    
    MetalOutwardMt findByUniqueId(Long uniqueId);
    
    MetalOutwardMt findByInvNoAndDeactive(String invNo, Boolean deactive);

}
