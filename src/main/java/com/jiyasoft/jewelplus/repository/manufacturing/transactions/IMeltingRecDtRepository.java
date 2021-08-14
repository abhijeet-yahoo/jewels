package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingMt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.MeltingRecDt;

public interface IMeltingRecDtRepository extends
		JpaRepository<MeltingRecDt, Integer>,
		QueryDslPredicateExecutor<MeltingRecDt> {
	
	List<MeltingRecDt> findByMeltingMtAndDeactive(MeltingMt meltingMt,Boolean deactive);
	
	MeltingRecDt findByUniqueId(Long uniqueId);

}
