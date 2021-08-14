package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Category;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductSize;


public interface IProductSizeRepository extends JpaRepository<ProductSize, Integer>,
QueryDslPredicateExecutor<ProductSize>{
	ProductSize findByName(String name);
	Page<ProductSize> findByCategory(Category category, Pageable pageable);

}
