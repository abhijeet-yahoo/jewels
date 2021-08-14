package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Quality;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.Shape;

public interface IQualityRepository extends JpaRepository<Quality, Integer>,
		QueryDslPredicateExecutor<Quality> {

	//Quality findByName(String name);

	Page<Quality> findByShape(Shape shape, Pageable pageable);

	Quality findByShapeAndNameAndDeactive(Shape shape, String name,Boolean deactive);
	
	Quality findByShapeAndName(Shape shape, String name);
	
	List<Quality> findByDeactiveOrderByNameAsc(Boolean deactive);
	
	// new method for intquality for description
	List<Quality> findByIntQuality(String intQuality);
	

}
