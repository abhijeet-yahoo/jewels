package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;

public interface ISizeGroupRepository extends
		JpaRepository<SizeGroup, Integer>, QueryDslPredicateExecutor<SizeGroup> {

	SizeGroup findByName(String name);

	SizeGroup findByNameAndDeactive(String name, Boolean deactive);
	
	Page<SizeGroup> findByShape(Shape shape, Pageable pageable);
	
	SizeGroup findByShapeAndNameAndDeactive(Shape shape, String sizeGroupName,Boolean deactive);
	
	//SizeGroup findByShapeAndName(Shape shape , String sizeGroupNm);

}
