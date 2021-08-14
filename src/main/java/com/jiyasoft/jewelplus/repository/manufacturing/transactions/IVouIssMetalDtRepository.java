package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMetalDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;

public interface IVouIssMetalDtRepository extends JpaRepository<VouIssMetalDt, Integer>, QueryDslPredicateExecutor<VouIssMetalDt> {

	List<VouIssMetalDt> findByVouIssMtAndDeactive(VouIssMt vouIssMt, Boolean deactive);
	
	List<VouIssMetalDt> findByVouIssDtAndDeactive(VouIssDt vouIssDt,Boolean deactive);
	
	VouIssMetalDt findByVouIssDtAndDeactiveAndMainMetal(VouIssDt vouIssDt,Boolean deactive ,Boolean mainMetal);
	
}
