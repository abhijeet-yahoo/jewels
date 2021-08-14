package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;


import com.jiyasoft.jewelplus.domain.manufacturing.masters.Color;

public interface IColorRepository extends JpaRepository<Color, Integer>, QueryDslPredicateExecutor<Color> {

	Color findByName(String name);
	
	Color findByDefColorAndDeactive(Boolean defColor, Boolean deactive);

}
