package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.LabourType;

public interface ILabourTypeRepository extends JpaRepository<LabourType, Integer>, QueryDslPredicateExecutor<LabourType> {
	
	LabourType findByName(String name);
	
	//LabourType findByCategoryAndLabourType(Category category,LabourType labourType);
	
	LabourType findByCodeAndDeactive(String code,Boolean deactive);

}
