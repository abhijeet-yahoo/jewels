package com.jiyasoft.jewelplus.repository.manufacturing.transactions;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardDt;
import com.jiyasoft.jewelplus.domain.manufacturing.transactions.StoneOutwardMt;

public interface IStoneOutwardDtRepository extends
		JpaRepository<StoneOutwardDt, Integer>,
		QueryDslPredicateExecutor<StoneOutwardDt> {

	List<StoneOutwardDt> findByStoneOutwardMt(StoneOutwardMt stoneOutwardMt);

	List<StoneOutwardDt> findBystoneOutwardMtAndDeactiveOrderByIdDesc(StoneOutwardMt stoneOutwardMt, Boolean deactive);
	
	
	Page<StoneOutwardDt> findByStoneOutwardMt(StoneOutwardMt stoneOutwardMt,
			Pageable pageable);

	StoneOutwardDt findByUniqueId(Long uniqueId);

}
