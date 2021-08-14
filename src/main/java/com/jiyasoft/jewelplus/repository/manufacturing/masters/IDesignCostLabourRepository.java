package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Design;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.DesignCostLabour;

public interface IDesignCostLabourRepository extends
		JpaRepository<DesignCostLabour, Integer>,
		QueryDslPredicateExecutor<DesignCostLabour> {

	List<DesignCostLabour> findByDesignAndDeactive(Design design,
			Boolean deactive);

}
