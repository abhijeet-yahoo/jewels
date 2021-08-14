package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Component;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Purity;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ComponentPurchaseMt;

public interface IComponentPurchaseDtRepository extends JpaRepository<ComponentPurchaseDt, Integer>, QueryDslPredicateExecutor<ComponentPurchaseDt>{

	List<ComponentPurchaseDt> findByComponentPurchaseMtAndDeactive(ComponentPurchaseMt componentPurchaseMt, Boolean deactive);
	
	List<ComponentPurchaseDt> findByMetalAndComponentAndPurityAndColorAndDeactive(Metal metal,Component component,Purity purity,Color color,Boolean deactive);
	
	ComponentPurchaseDt findByUniqueId(Long uniqueId);

}
