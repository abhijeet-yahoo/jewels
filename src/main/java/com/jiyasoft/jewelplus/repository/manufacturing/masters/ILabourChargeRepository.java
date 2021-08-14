package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourCharge;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Party;

public interface ILabourChargeRepository extends
		JpaRepository<LabourCharge, Integer>,
		QueryDslPredicateExecutor<LabourCharge> {

	
	LabourCharge findByRate(Double rate);

	Page<LabourCharge> findByCategory(Category category, Pageable pageable);
	
	List<LabourCharge> findByCategoryAndLabourType(Category category,LabourType labourType);
	
	List<LabourCharge> findByCategoryAndDeactive(Category category,Boolean deactive);
	List<LabourCharge> findByCategoryAndDeactiveAndParty(Category category,Boolean deactive,Party party);
	
	List<LabourCharge> findByDefLabour(Boolean defLabour);
	
	List<LabourCharge> findByPartyAndCategoryAndLabourType(Party party,Category category,LabourType labourType);
	List<LabourCharge> findByPartyAndCategoryAndLabourTypeAndDeactive(Party party,Category category,LabourType labourType,Boolean deactive);
	
	List<LabourCharge> findByPartyAndCategoryAndMetalAndDeactive(Party party,Category category,Metal metal,Boolean deactive);
	
	
}
