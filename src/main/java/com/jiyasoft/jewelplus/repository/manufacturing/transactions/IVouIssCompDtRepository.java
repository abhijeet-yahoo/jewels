package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssCompDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;

public interface IVouIssCompDtRepository extends JpaRepository<VouIssCompDt, Integer>, QueryDslPredicateExecutor<VouIssCompDt>{

	List<VouIssCompDt> findByVouIssMtAndDeactive(VouIssMt vouIssMt,Boolean deactive);
	
	List<VouIssCompDt> findByVouIssDtAndDeactive(VouIssDt vouIssDt, Boolean deacctive);
}
