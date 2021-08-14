package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnBflMt;


public interface IStnBflDtRepository  extends JpaRepository<StnBflDt, Integer>,QueryDslPredicateExecutor<StnBflDt> {
	

	List<StnBflDt> findByStnBflMt(StnBflMt stnBflMt);

	Page<StnBflDt> findByStnBflMt(StnBflMt stnBflMt,Pageable pageable);

	List<StnBflDt> findByStnBflMtAndDeactiveOrderByIdDesc(StnBflMt stnBflMt, Boolean deactive);
	
	StnBflDt findByUniqueId(Long uniqueId);


}
