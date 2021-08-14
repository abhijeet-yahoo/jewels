package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseRetMt;

public interface IStnLooseRetDtRepository extends JpaRepository<StnLooseRetDt, Integer>, QueryDslPredicateExecutor<StnLooseRetDt> {

	List<StnLooseRetDt> findByStnLooseRetMt(StnLooseRetMt stnLooseRetMt);
	
	List<StnLooseRetDt> findByRefTranId(Integer refTranId);
}
