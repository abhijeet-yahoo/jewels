package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SubShape;

public interface ISubShapeRepository extends JpaRepository<SubShape, Integer>,
		QueryDslPredicateExecutor<SubShape> {
	
	SubShape findByName(String name);

	Page<SubShape> findByShape(Shape shape, Pageable pageable);
	
	SubShape findByShapeAndName(Shape shape, String subShapeName);


}
