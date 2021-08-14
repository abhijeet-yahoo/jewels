package com.jiyasoft.jewelplus.repository.marketing.transactions;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.jiyasoft.jewelplus.domain.manufacturing.masters.Metal;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackLabDt;
import com.jiyasoft.jewelplus.domain.marketing.transactions.PackMt;

public interface IPackLabDtRepository extends JpaRepository<PackLabDt,Integer>,
QueryDslPredicateExecutor<PackLabDt>{
	
	List<PackLabDt>findByPackDt(PackDt packDt);

	List<PackLabDt> findByPackDtAndMetal(PackDt packDt,Metal metal);
	
	List<PackLabDt>findByPackMt(PackMt packMt);
	
}
