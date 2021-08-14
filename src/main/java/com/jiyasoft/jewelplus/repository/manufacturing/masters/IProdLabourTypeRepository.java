package com.jiyasoft.jewelplus.repository.manufacturing.masters;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Department;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProdLabourType;

public interface IProdLabourTypeRepository extends
		JpaRepository<ProdLabourType, Integer>,
		QueryDslPredicateExecutor<ProdLabourType> {

	ProdLabourType findByName(String name);
	
	Page<ProdLabourType> findByCategory(Category category, Pageable pageable);
	
	Page<ProdLabourType> findByCategoryAndDepartment(Category category,Department department, Pageable pageable);
	
}
