package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;

public interface ICategoryRepository extends JpaRepository<Category, Integer>, QueryDslPredicateExecutor<Category> {

	Category findByName(String name);
	
	Category findByCategCode(String code);

}
