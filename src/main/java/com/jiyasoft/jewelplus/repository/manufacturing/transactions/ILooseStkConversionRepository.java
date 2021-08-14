package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.LooseStkConversion;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;

public interface ILooseStkConversionRepository extends JpaRepository<LooseStkConversion, Integer>,QueryDslPredicateExecutor<LooseStkConversion> {
	
	List<LooseStkConversion> findByStnLooseDt(StnLooseDt stnLooseDt);
	
	List<LooseStkConversion> findByStnLooseMt(StnLooseMt stnLooseMt);

}
