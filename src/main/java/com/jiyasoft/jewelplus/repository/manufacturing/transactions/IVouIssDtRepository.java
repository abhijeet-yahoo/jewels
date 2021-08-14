package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.VouIssMt;

public interface IVouIssDtRepository extends JpaRepository<VouIssDt, Integer>,QueryDslPredicateExecutor<VouIssDt>{
	
	List<VouIssDt> findByVouIssMtAndDeactive(VouIssMt vouIssMt,Boolean deactive);

	
}
