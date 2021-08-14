package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.BagMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.ReadyBagIssMt;

public interface IReadyBagIssDtRepository extends JpaRepository<ReadyBagIssDt, Integer>, QueryDslPredicateExecutor<ReadyBagIssDt> {

	List<ReadyBagIssDt> findByReadyBagIssMt(ReadyBagIssMt readyBagIssMt);
	
	List<ReadyBagIssDt> findByBagMt(BagMt bagMt);
	
}
