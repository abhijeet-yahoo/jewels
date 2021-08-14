package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubCategory;

public interface ISubCategoryRepository extends JpaRepository<SubCategory, Integer>, QueryDslPredicateExecutor<SubCategory> {
	
	SubCategory findByName(String name);

	Page<SubCategory> findByCategory(Category category, Pageable pageable);
	
	List<SubCategory> findByDeactive(Boolean deactive);

}
