package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.masters.ProcessMast;

public interface IProcessMastRepository extends JpaRepository<ProcessMast, Integer>, QueryDslPredicateExecutor<ProcessMast> {

	

}
