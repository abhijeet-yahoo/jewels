package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.SizeGroup;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.StoneChart;

public interface IStoneChartRepository extends
		JpaRepository<StoneChart, Integer>,
		QueryDslPredicateExecutor<StoneChart> {

	StoneChart findBySize(String size);

	StoneChart findByShapeAndSizeAndDeactive(Shape shape, String size,Boolean deactive);
	
	StoneChart findByShapeAndSizeAndSieveAndDeactive(Shape shape, String size,String sieve,Boolean deactive);
	
	List<StoneChart> findByShape(Shape shape);

	List<StoneChart> findBySizeGroupAndDeactive(SizeGroup sizeGroup,Boolean deactive);
	
	Page<StoneChart> findByShapeAndDeactive(Shape shape,Boolean deactive, Pageable pageable);
	
	List<StoneChart> findBySizeGroupAndSizeAndDeactive(SizeGroup sizeGroup,String size,Boolean deactive);
	
	StoneChart findByShapeAndSieveAndDeactive(Shape shape, String sieve,Boolean deactive);
	
	StoneChart findByShapeAndSizeAndSieveAndSizeGroupAndDeactive(Shape shape, String size,String sieve,SizeGroup sizeGroup,Boolean deactive);
}
