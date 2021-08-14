package com.jiyasoft.jewelplus.repository.manufacturing.masters.reports;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.reports.DesignRpt;

public interface IDesignRptRepository extends
		JpaRepository<DesignRpt, Integer>, QueryDslPredicateExecutor<DesignRpt> {

	//Page<DesignRpt> findById(List<DesignRpt> designRptList,Pageable pageable);
	
	//Page<DesignRpt> findAll(List<DesignRpt> designRptList,Pageable pageable);
	
	//Page<DesignRpt> findByDesignRpts(DesignRpt designRptList,Pageable pageable);

}
