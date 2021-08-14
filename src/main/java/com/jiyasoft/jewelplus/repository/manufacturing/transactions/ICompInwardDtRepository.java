package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.CompInwardMt;

public interface ICompInwardDtRepository extends
		JpaRepository<CompInwardDt, Integer>,
		QueryDslPredicateExecutor<CompInwardDt> {

	 List<CompInwardDt> findByCompInwardMt(CompInwardMt compInwardMt);

	 Page<CompInwardDt> findByCompInwardMt(CompInwardMt compInwardMt,Pageable pageable);
	
	 CompInwardDt findByUniqueId(Long uniqueId );
	 
	 List<CompInwardDt> findByComponentAndPurityAndColorAndDeactive(Component component,Purity purity,Color color,Boolean deactive);
	 
	 List<CompInwardDt> findByCompInwardMtAndDeactive(CompInwardMt compInwardMt,Boolean deactive);
	 
	 
}
