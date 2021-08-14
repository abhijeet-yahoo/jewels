package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;

public interface IDepartmentRepository extends JpaRepository<Department, Integer>,QueryDslPredicateExecutor<Department> {
	
	Department findByName(String name);
	
	Department findByCodeAndDeactive(String code, Boolean deactive);

	List<Department> findByLooseMetalStkOrComponentStkOrStoneStkOrderByNameAsc(Boolean looseMetalStk,Boolean componentStk,Boolean stoneStk);
	
	
	
	List<Department> findByVouTypeFlgAndDeactiveOrderByNameAsc(Boolean vouTypeFlg,Boolean deactive);
	
	
	List<Department> findByBarcodeFlg(Boolean barcodeFlg);
	
}
