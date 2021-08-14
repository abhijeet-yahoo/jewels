package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.FgRetMt;

public interface IFgRetDtRepository extends JpaRepository<FgRetDt, Integer>, QueryDslPredicateExecutor<FgRetDt>{

	List<FgRetDt> findByFgRetMt(FgRetMt fgRetMt);
	
	List<FgRetDt> findByFgRetMtAndDeactiveOrderByIdDesc(FgRetMt fgRetMt, Boolean deactive);
	
	FgRetDt findByTranMtId(Integer tranMtId);

}
