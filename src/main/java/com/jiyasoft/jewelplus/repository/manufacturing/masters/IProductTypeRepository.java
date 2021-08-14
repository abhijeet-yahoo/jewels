package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProductTypeMast;

public interface IProductTypeRepository extends JpaRepository<ProductTypeMast, Integer> ,QueryDslPredicateExecutor<ProductTypeMast> {

	ProductTypeMast findByNameAndDeactive(String name,Boolean deactive);
	
	ProductTypeMast findByCodeAndDeactive(String code, Boolean deactive);
	
	List<ProductTypeMast> findAllByOrderByNameAsc();
}
