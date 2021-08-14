package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StnLooseMt;

public interface IStnLooseDtRepository extends JpaRepository<StnLooseDt, Integer>,QueryDslPredicateExecutor<StnLooseDt>{

	List<StnLooseDt> findByStnLooseMt(StnLooseMt stnLooseMt);
}
