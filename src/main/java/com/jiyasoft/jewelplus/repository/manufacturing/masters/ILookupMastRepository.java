package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.LookUpMast;

public interface ILookupMastRepository  extends JpaRepository<LookUpMast, Integer>,QueryDslPredicateExecutor<LookUpMast>{
	
	List<LookUpMast> findByName(String name);
	
	LookUpMast findByNameAndFieldValueAndDeactive(String name,String fieldValue,Boolean deactive);
	
	LookUpMast findByFieldValueAndDeactive(String fieldValue,Boolean deactive);
	
	LookUpMast findByNameAndCodeAndDeactive(String name,String code,Boolean deactive);
	
	
	
	
}
