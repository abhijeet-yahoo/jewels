package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssLabDt;

public interface IVouIssLabDtRepository extends JpaRepository<VouIssLabDt, Integer> , QueryDslPredicateExecutor<VouIssLabDt>{

	List<VouIssLabDt> findByVouIssDtAndDeactive(VouIssDt vouIssDt,Boolean deactive);
	
	List<VouIssLabDt> findByVouIssDtAndMetalAndDeactive(VouIssDt vouIssDt, Metal metal, Boolean deactive);
	
	
	
}
