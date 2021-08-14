package com.jiyasoft.jewelplus.repository.manufacturing.masters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.BranchMaster;

public interface IBranchRepository extends JpaRepository<BranchMaster, Integer>, QueryDslPredicateExecutor<BranchMaster> {

	BranchMaster findById(Integer id);
	
	BranchMaster findByCodeAndDeactive(String code, Boolean deactive);
	
	BranchMaster findByNameAndDeactive(String name,Boolean deactive);
}
