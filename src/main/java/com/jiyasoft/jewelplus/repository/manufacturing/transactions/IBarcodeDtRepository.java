package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BarcodeMt;

public interface IBarcodeDtRepository extends JpaRepository<BarcodeDt, Integer>, QueryDslPredicateExecutor<BarcodeDt> {

	List<BarcodeDt> findByBarcodeMt(BarcodeMt barcodeMt);
	
	List<BarcodeDt> findByBarcodeMtAndDeactive(BarcodeMt barcodeMt,Boolean deactive);
}
