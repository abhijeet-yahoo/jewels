package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;

public interface IShapeRepository extends JpaRepository<Shape, Integer>, QueryDslPredicateExecutor<Shape> {
	
	Shape findByName(String name);

}
